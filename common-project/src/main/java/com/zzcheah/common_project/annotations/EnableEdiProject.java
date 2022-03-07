package com.zzcheah.common_project.annotations;

import com.zzcheah.common_project.configurations.FirebaseConfig;
import com.zzcheah.common_project.services.EdiService;
import com.zzcheah.common_project.services.FirebaseService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(TYPE)
@Import(EnableEdiProject.Config.class)
public @interface EnableEdiProject {

    Class<?>[] excludes() default {};

    class Config implements ImportBeanDefinitionRegistrar {

        static final Class<?>[] defaultClasses = new Class[]{
                EdiService.class,
                FirebaseConfig.class,
                FirebaseService.class,
        };

        static final BeanNameGenerator BEAN_NAME_GENERATOR = AnnotationBeanNameGenerator.INSTANCE;


        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

            final AnnotationAttributes attributes = new AnnotationAttributes(
                    Objects.requireNonNull(metadata.getAnnotationAttributes(EnableEdiProject.class.getCanonicalName())));

            final Class<?>[] excludes = attributes.getClassArray("excludes");

            for (Class<?> aclass : defaultClasses) {
                if (Arrays.stream(excludes).noneMatch(ex -> ex == aclass)) {
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(aclass);
                    final String beanClassName = BEAN_NAME_GENERATOR.generateBeanName(builder.getBeanDefinition(), registry);
                    if (!registry.containsBeanDefinition(beanClassName)) {
                        registry.registerBeanDefinition(beanClassName, builder.getBeanDefinition());
                    }
                }
            }
        }


    }

}
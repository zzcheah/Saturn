package com.zzcheah.system_mock1.annotations;

import com.zzcheah.system_mock1.components.System1EdiProcessor;
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
@Import(EnableSystem1EdiProcessor.Config.class)
public @interface EnableSystem1EdiProcessor {

    Class<?>[] excludes() default {};

    class Config implements ImportBeanDefinitionRegistrar {

        static final Class<?>[] defaultClasses = new Class[]{System1EdiProcessor.class};

        static final BeanNameGenerator BEAN_NAME_GENERATOR = AnnotationBeanNameGenerator.INSTANCE;


        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

            final AnnotationAttributes attributes = new AnnotationAttributes(
                    Objects.requireNonNull(metadata.getAnnotationAttributes(EnableSystem1EdiProcessor.class.getCanonicalName())));

            final Class<?>[] excludes = attributes.getClassArray("excludes");

            Arrays.stream(defaultClasses)
                    .filter(aclass -> Arrays.stream(excludes).noneMatch(ex -> ex == aclass))
                    .forEach(aClass -> {
                        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(aClass);
                        final String beanClassName = BEAN_NAME_GENERATOR.generateBeanName(builder.getBeanDefinition(), registry);
                        if (!registry.containsBeanDefinition(beanClassName)) {
                            registry.registerBeanDefinition(beanClassName, builder.getBeanDefinition());
                        }
                    });
        }


    }

}
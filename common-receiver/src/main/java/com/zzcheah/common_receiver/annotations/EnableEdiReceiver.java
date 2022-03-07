package com.zzcheah.common_receiver.annotations;

import com.zzcheah.common_receiver.configurations.FirebaseConfig;
import com.zzcheah.common_receiver.services.FirebaseService;
import com.zzcheah.common_receiver.services.ReceiverService;
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
@Import(EnableEdiReceiver.Config.class)
public @interface EnableEdiReceiver {

    Class<?>[] excludes() default {};

    class Config implements ImportBeanDefinitionRegistrar {

        static final Class<?>[] defaultClasses = new Class[]{
                FirebaseConfig.class,
                FirebaseService.class,
                ReceiverService.class,
        };

        static final BeanNameGenerator BEAN_NAME_GENERATOR = AnnotationBeanNameGenerator.INSTANCE;


        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

            final AnnotationAttributes attributes = new AnnotationAttributes(
                    Objects.requireNonNull(metadata.getAnnotationAttributes(EnableEdiReceiver.class.getCanonicalName())));

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
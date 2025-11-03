package asw.bettermusic.recensioniseguite.config;

/* 
 * see: https://stackoverflow.com/questions/50208018/how-to-read-qualifier-from-property-file-in-spring-boot
 */ 

import java.lang.annotation.Annotation;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AutowireCandidateResolverConfigurer implements BeanFactoryPostProcessor {
    private static class EnvironmentAwareQualifierAnnotationAutowireCandidateResolver extends QualifierAnnotationAutowireCandidateResolver {

        private static class ResolvedQualifier implements Qualifier {
            private final String value;
			
            ResolvedQualifier(String value) { this.value = value; }
            
			@Override
            public String value() { return this.value; }
            
			@Override
            public Class<? extends Annotation> annotationType() { return Qualifier.class; }
        }

        @Override
        protected boolean checkQualifier(BeanDefinitionHolder bdHolder, Annotation annotation, TypeConverter typeConverter) {
            if (annotation instanceof Qualifier) {
                Qualifier qualifier = (Qualifier) annotation;
                if (qualifier.value().startsWith("${") && qualifier.value().endsWith("}")) {
                    DefaultListableBeanFactory bf = (DefaultListableBeanFactory) this.getBeanFactory();
                    ResolvedQualifier resolvedQualifier = new ResolvedQualifier(bf.resolveEmbeddedValue(qualifier.value()));
                    return super.checkQualifier(bdHolder, resolvedQualifier, typeConverter);
                }
            }
            return super.checkQualifier(bdHolder, annotation, typeConverter);
        }
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        DefaultListableBeanFactory bf = (DefaultListableBeanFactory) beanFactory;
        bf.setAutowireCandidateResolver(new EnvironmentAwareQualifierAnnotationAutowireCandidateResolver());
    }
}

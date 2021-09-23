package thelameres.magint.launcher.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import thelameres.magnit.api.models.repositories.EntryRepository;
import thelameres.magnit.api.xml.XmlParser;
import thelameres.magnit.api.xml.XmlProcessor;
import thelameres.magnit.api.xml.XmlTransformer;
import thelameres.magnit.lib.models.repositories.EntryRepositoryImpl;
import thelameres.magnit.lib.xml.XmlParserImpl;
import thelameres.magnit.lib.xml.XmlProcessorImpl;
import thelameres.magnit.lib.xml.XmlTransformerImpl;

import javax.sql.DataSource;
import javax.xml.bind.JAXBException;
import java.lang.reflect.Field;
import java.sql.SQLException;

import static java.util.Optional.ofNullable;

@Configuration
@ConfigurationPropertiesScan
public class BeansConfiguration {

    @Bean
    @Autowired
    public EntryRepository entryRepository(DataSource dataSource) throws SQLException {
        return new EntryRepositoryImpl(dataSource);
    }

    @Bean
    public XmlProcessor xmlProcessor() throws JAXBException {
        return new XmlProcessorImpl();
    }

    @Bean
    public XmlTransformer xmlTransformer() {
        return new XmlTransformerImpl();
    }

    @Bean
    public XmlParser xmlParser() throws JAXBException {
        return new XmlParserImpl();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(ofNullable(injectionPoint.getMethodParameter())
                .<Class>map(MethodParameter::getContainingClass)
                .orElseGet(() ->
                        ofNullable(injectionPoint.getField())
                                .map(Field::getDeclaringClass)
                                .orElseThrow(IllegalArgumentException::new)
                )
        );
    }
}

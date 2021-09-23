package thelameres.magint.launcher;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import thelameres.magint.launcher.config.MagnitProperties;
import thelameres.magnit.api.models.entities.Entry;
import thelameres.magnit.api.models.repositories.EntryRepository;
import thelameres.magnit.api.xml.XmlParser;
import thelameres.magnit.api.xml.XmlProcessor;
import thelameres.magnit.api.xml.XmlTransformer;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class LauncherApplication {

    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }

    @Bean
    @Autowired
    public ApplicationRunner applicationRunner(Logger logger,
                                               EntryRepository entryRepository,
                                               XmlProcessor xmlProcessor,
                                               XmlTransformer xmlTransformer,
                                               XmlParser xmlParser,
                                               MagnitProperties magnitProperties) {
        return args -> {
            logger.debug("Hello");
            logger.debug("Generate Table");
            entryRepository.generateTable();
            if (!entryRepository.count().equals(BigDecimal.ZERO)) entryRepository.clear();
            entryRepository.generateData(magnitProperties.getCount());
            List<Entry> entries = entryRepository.listAll();
            //BigDecimal reduce = entries.stream().parallel().map(Entry::getField).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
            //BigDecimal sum = entryRepository.sum();
            //logger.debug("Sum of field from database: {}", sum);
            //logger.debug("Sum of from application and database function is {}", sum.equals(reduce));
            File parse = xmlProcessor.parse(entries, magnitProperties.getFirstFileName());
            xmlTransformer.transform(parse.getAbsolutePath(), magnitProperties.getSecondFileName());
            BigDecimal sum1 = xmlParser.sum(magnitProperties.getSecondFileName());
            logger.info("Sum of from file: {}", sum1);
        };
    }

}

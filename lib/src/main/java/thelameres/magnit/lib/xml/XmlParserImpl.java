package thelameres.magnit.lib.xml;

import thelameres.magnit.api.models.entities.EntriesValues;
import thelameres.magnit.api.models.entities.EntryValue;
import thelameres.magnit.api.xml.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;

public class XmlParserImpl implements XmlParser {
    private final JAXBContext jaxbContext;

    public XmlParserImpl() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(EntriesValues.class);
    }

    public BigDecimal sum(String fileName) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        EntriesValues unmarshal = (EntriesValues) unmarshaller.unmarshal(new File(fileName));
        return unmarshal.getEntryValues().stream().parallel().map(EntryValue::getField).map(BigDecimal::new).reduce(BigDecimal.ONE, BigDecimal::add);
    }
}

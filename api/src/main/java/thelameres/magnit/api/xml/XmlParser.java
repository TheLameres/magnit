package thelameres.magnit.api.xml;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

public interface XmlParser {
    BigDecimal sum(String fileName) throws JAXBException;
}

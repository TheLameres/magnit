package thelameres.magnit.api.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface XmlTransformer {
    void transform(String input, String output) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

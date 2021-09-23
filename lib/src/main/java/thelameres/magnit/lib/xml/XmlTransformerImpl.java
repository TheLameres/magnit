package thelameres.magnit.lib.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import thelameres.magnit.api.xml.XmlTransformer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlTransformerImpl implements XmlTransformer {
    DocumentBuilderFactory factory;

    public XmlTransformerImpl() {
        factory = DocumentBuilderFactory.newInstance();
    }

    public void transform(String input, String output) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder builder2 = factory.newDocumentBuilder();
        Document document2 = builder2.parse(input);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource styleSource = new StreamSource(getClass().getClassLoader().getResourceAsStream("convert.xsl"));
        Transformer transformer = tFactory.newTransformer(styleSource);
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
        DOMSource source = new DOMSource(document2);
        transformer.transform(source, new StreamResult(new FileOutputStream(output)));
    }
}

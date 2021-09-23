package thelameres.magnit.api.xml;

import thelameres.magnit.api.models.entities.Entry;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public interface XmlProcessor {
    File parse(List<Entry> entries, String pathname) throws JAXBException;
}

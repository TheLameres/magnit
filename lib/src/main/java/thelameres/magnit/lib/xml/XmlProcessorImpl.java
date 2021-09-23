package thelameres.magnit.lib.xml;

import thelameres.magnit.api.models.entities.Entries;
import thelameres.magnit.api.models.entities.Entry;
import thelameres.magnit.api.xml.XmlProcessor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XmlProcessorImpl implements XmlProcessor {
    private final JAXBContext jaxbContext;

    public XmlProcessorImpl() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Entries.class);
    }

    @Override
    public File parse(List<Entry> entryList, String pathname) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        Entries entries = new Entries();
        entries.setEntries(entryList);
        File file = new File(pathname);
        marshaller.marshal(entries, file);
        return file;
    }
}

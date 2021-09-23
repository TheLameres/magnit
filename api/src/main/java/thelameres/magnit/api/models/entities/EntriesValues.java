package thelameres.magnit.api.models.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntriesValues {
    @XmlElement(name = "entry")
    List<EntryValue> entryValues;

    public EntriesValues() {
    }

    public List<EntryValue> getEntryValues() {
        return entryValues;
    }

    public void setEntryValues(List<EntryValue> entryValues) {
        this.entryValues = entryValues;
    }
}

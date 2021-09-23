package thelameres.magnit.api.models.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    private Long field;

    public Long getField() {
        return field;
    }

    public void setField(Long field) {
        this.field = field;
    }

    public Entry() {
    }

    public Entry(Long field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return getField() != null ? getField().equals(entry.getField()) : entry.getField() == null;
    }

    @Override
    public int hashCode() {
        return getField() != null ? getField().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "field=" + field +
                '}';
    }
}

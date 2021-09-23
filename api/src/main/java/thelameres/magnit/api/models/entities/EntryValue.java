package thelameres.magnit.api.models.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryValue {
    @XmlAttribute(name = "field")
    private Long field;

    public EntryValue() {
    }

    public Long getField() {
        return field;
    }

    public void setField(Long field) {
        this.field = field;
    }
}

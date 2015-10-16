package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "phone")
@Entity
public class Phone implements Serializable {

    @Id
    private String number;

    private String description;

    @ManyToOne
    private InfoEntity infoEntity;

    public Phone() {
    }

    public Phone(String number, String description) {
        this.number = number;
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoEntity getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }
}

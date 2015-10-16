package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AddressId implements Serializable {

    String street;
    String additionalInfo;

    public AddressId() {
    }

    public AddressId(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "AddressId{" + "street=" + street + ", additionalInfo=" + additionalInfo + '}';
    }
}

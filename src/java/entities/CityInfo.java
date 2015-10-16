package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "cityinfo")
@Entity
public class CityInfo implements Serializable {

    @Id
    private String zipCode;

    private String city;

    @OneToMany(mappedBy = "cityInfo", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Address> addresses = new ArrayList();

    public CityInfo() {
    }

    public CityInfo(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address a) {
        addresses.add(a);
    }
}

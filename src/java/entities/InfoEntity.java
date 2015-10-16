package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "infoentity")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    @OneToMany(mappedBy = "infoEntity", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Phone> phones = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    public InfoEntity() {
    }

    public InfoEntity(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public InfoEntity(String email) {
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        phone.setInfoEntity(this);
        phones.add(phone);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
//        address.addInfoEntity(this); // this fixed "editCompany". see addInfoEntity in Address
        this.address = address;
    }

    public void setPhones(List<Phone> phones) {
        for (Phone phone : phones) {
            phone.setInfoEntity(this);
        }
        this.phones = phones;
    }
}

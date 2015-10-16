package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @EmbeddedId
    private AddressId addressId;

    @OneToMany(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<InfoEntity> infoEntities = new ArrayList();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private CityInfo cityInfo;

    public Address() {
    }

    public Address(AddressId addressId, CityInfo cityInfo) {
        this.addressId = addressId;
        this.cityInfo = cityInfo;
    }

    public void setInfoEntities(List<InfoEntity> infoEntities) {
        this.infoEntities = infoEntities;
    }

    public List<InfoEntity> getInfoEntities() {
        return infoEntities;
    }

    public void addInfoEntity(InfoEntity ie) {
        infoEntities.add(ie);
        ie.setAddress(this);
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        cityInfo.addAddress(this);
        this.cityInfo = cityInfo;
    }

    public AddressId getAddressId() {
        return addressId;
    }

    public void setAddressId(AddressId addressId) {
        this.addressId = addressId;
    }
}

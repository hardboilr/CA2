package entities;

import javax.persistence.Entity;

/**
 * @author sebastiannielsen
 */
@Entity
public class Company extends InfoEntity {
    
    private long cvr;
    private String name;
    private String description;
    private int NumEmployees;
    private long marketValue;
    
    public Company(){
    }

    public Company(long cvr, String name, String description, int NumEmployees, long marketValue) {
        this.cvr = cvr;
        this.name = name;
        this.description = description;
        this.NumEmployees = NumEmployees;
        this.marketValue = marketValue;
    }

    public long getCvr() {
        return cvr;
    }

    public void setCvr(long cvr) {
        this.cvr = cvr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumEmployees() {
        return NumEmployees;
    }

    public void setNumEmployees(int NumEmployees) {
        this.NumEmployees = NumEmployees;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(long marketValue) {
        this.marketValue = marketValue;
    }
    
    
}

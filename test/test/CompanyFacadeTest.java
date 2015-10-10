package test;

import deploy.DeploymentConfiguration;
import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Phone;
import exception.CompanyNotFoundException;
import exception.PhoneExistException;
import facade.CompanyFacade;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CompanyFacadeTest {
    
    CompanyFacade facade;

    public CompanyFacadeTest() {
    }
    @BeforeClass
    public static void setUp(){
        DeploymentConfiguration.setTestModeOn();
    }

    @Before
    public void setUpDatabase() {
        facade = new CompanyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        EntityManager em = facade.getEntityManager();
    }

    @Test
    public void testAddCompany() throws CompanyNotFoundException, PhoneExistException {
        Company company = new Company(12345, "firma", "description", 15, 300000l);
        Phone phone = new Phone("34565948","Mobil");
        company.addPhone(phone);
        
        CityInfo ci = new CityInfo(8643, "Ans By");
        Address address = new Address("2 Scott Trail","1 tv");
        address.setCityInfo(ci);
        company.setAddress(address);
        
        facade.createCompany(company);
        
        assertEquals("firma", company.getName());
        company = facade.getCompany(company.getCvr());
        assertEquals("firma", company.getName());
        assertEquals("Mobil", company.getPhones().get(0).getDescription());
        assertEquals("34565948", company.getPhones().get(0).getNumber());
        assertEquals("2 Scott Trail", company.getAddress().getStreet());
        assertEquals("Ans By", company.getAddress().getCityInfo().getCity());
        facade.deleteCompany(company.getCvr());
    }
    
    @Test (expected = PhoneExistException.class)
    public void testAddCompanyWithExistingPhoneNumber() throws CompanyNotFoundException, PhoneExistException {
        Company company = new Company(12345, "firma", "description", 15, 300000l);
        Phone phone = new Phone("00307254","Mobil");
        company.addPhone(phone);
        
        CityInfo ci = new CityInfo(8643, "Ans By");
        Address address = new Address("2 Scott Trail","1 tv");
        address.setCityInfo(ci);
        company.setAddress(address);
        
        facade.createCompany(company);
        assertEquals("Mobil", company.getPhones().get(0).getDescription());
        facade.deleteCompany(company.getCvr());
    }

    @Test
    public void testGetCompany() throws CompanyNotFoundException, PhoneExistException {
        Company company = new Company(12345, "firma", "description", 15, 300000l);
        Phone phone = new Phone("34565948","Mobil");
        company.addPhone(phone);
        
        CityInfo ci = new CityInfo(8643, "Ans By");
        Address address = new Address("2 Scott Trail","1 tv");
        address.setCityInfo(ci);
        company.setAddress(address);
        
        facade.createCompany(company);
        Company c = facade.getCompany(12345l);
        assertEquals("firma", c.getName());
        facade.deleteCompany(company.getCvr());
    }
    
    @Test(expected = CompanyNotFoundException.class)
    public void testGetCompanyError() throws CompanyNotFoundException {
        facade.getCompany(23l);
    }

    @Test
    public void testGetCompanies() throws CompanyNotFoundException {
        assertEquals(1000, facade.getCompanies().size());
    }

    @Test
    public void testEditCompany() throws CompanyNotFoundException, PhoneExistException {
        Company company = new Company(12345, "firma", "description", 15, 300000l);
        Phone phone = new Phone("34565948","Mobil");
        company.addPhone(phone);
        
        CityInfo ci = new CityInfo(8643, "Ans By");
        Address address = new Address("2 Scott Trail","1 tv");
        address.setCityInfo(ci);
        company.setAddress(address);
        
        facade.createCompany(company);
        Company com = facade.getCompany(12345l);
        company.setName("firmaEdited");
        facade.editCompany(company);
        assertEquals("firmaEdited", facade.getCompany(12345l).getName());
        facade.deleteCompany(company.getCvr());
    }
    
    
}

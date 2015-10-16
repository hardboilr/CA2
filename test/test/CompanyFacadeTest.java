package test;

import deploy.DeploymentConfiguration;
import entities.Address;
import entities.AddressId;
import entities.CityInfo;
import entities.Company;
import entities.Phone;
import exception.NotFoundException;
import exception.ExistException;
import facade.CompanyFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CompanyFacadeTest {

    CompanyFacade facade = new CompanyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    
    private final int CVR_0 = 53924216;
    private final int CVR_1 = 52698711;
    private final int CVR_2 = 56983231;
    private final int CVR_3 = 11258832;
    private final int CVR_4 = 14559812;

    public CompanyFacadeTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        DeploymentConfiguration.setTestModeOn();
    }
    
    @Test
    public void testCreateCompany() throws NotFoundException, ExistException {
        int cvr = 33331546;
        String name = "Test create company";
        String description = "Some random description";
        int numEmployees = 15;
        long marketValue = 1000000l;
        String email = "niels@nielsen.dk";
        CityInfo cityInfo = new CityInfo("2500", "Valby");
        AddressId addressId = new AddressId("Test street 14", "3.th.");
        Address address = new Address(addressId, cityInfo);
        Phone phone1 = new Phone("64548432", "totally random description");
        Phone phone2 = new Phone("11111654", "another totally random description");
        Company company = new Company(cvr, name, description, numEmployees, marketValue);
        company.addPhone(phone1);
        company.addPhone(phone2);
        company.setEmail(email);
        company.setAddress(address);
        facade.createCompany(company);
        assertEquals(name, company.getName());
        assertEquals(cvr, company.getCvr());
        company = facade.getCompany(company.getCvr());
        assertEquals(name, company.getName());
        assertEquals(cvr, company.getCvr());
    }

    @Test(expected = ExistException.class)
    public void testCreateCompanyCvrExistException() throws NotFoundException, ExistException {
        String name = "Cvr exists!";
        String description = "Some random description";
        int numEmployees = 15;
        long marketValue = 1000000l;
        String email = "niels@nielsen.dk";
        CityInfo cityInfo = new CityInfo("2500", "Valby");
        AddressId addressId = new AddressId("Test street 14", "3.th.");
        Address address = new Address(addressId, cityInfo);
        Phone phone1 = new Phone("64548432", "totally random description");
        Phone phone2 = new Phone("11111654", "another totally random description");
        Company company = new Company(CVR_2, name, description, numEmployees, marketValue);
        company.addPhone(phone1);
        company.addPhone(phone2);
        company.setEmail(email);
        company.setAddress(address);
        facade.createCompany(company);
    }

    @Test(expected = ExistException.class)
    public void testCreateCompanyPhoneExistException() throws NotFoundException, ExistException {
        int cvr = 99999999;
        String name = "Phone exists!";
        String description = "Some random description";
        int numEmployees = 15;
        long marketValue = 1000000l;
        String email = "niels@nielsen.dk";
        CityInfo cityInfo = new CityInfo("2500", "Valby");
        AddressId addressId = new AddressId("Test street 14", "3.th.");
        Address address = new Address(addressId, cityInfo);
        Phone phone1 = new Phone("12345678", "totally random description");
        Company company = new Company(cvr, name, description, numEmployees, marketValue);
        company.addPhone(phone1);
        company.setEmail(email);
        company.setAddress(address);
        facade.createCompany(company);
    }

    @Test
    public void testCreateCompanyAddressExist() throws NotFoundException, ExistException {
        int cvr = 10000000;
        String name = "Address exists!";
        String description = "Some random description";
        int numEmployees = 15;
        long marketValue = 1000000l;
        String email = "niels@nielsen.dk";
        String zipCode = "1915";
        String city = "Frederiksberg C";
        String street = "H. C. Ørsteds Vej 74";
        String additionalInfo = "st.tv.";
        CityInfo cityInfo = new CityInfo(zipCode, city);
        AddressId addressId = new AddressId(street, additionalInfo);
        Address address = new Address(addressId, cityInfo);
        Phone phone1 = new Phone("11111111", "my phone number with only ones");
        Company company = new Company(cvr, name, description, numEmployees, marketValue);
        company.addPhone(phone1);
        company.setEmail(email);
        company.setAddress(address);
        facade.createCompany(company);
        assertEquals(cvr, company.getCvr());
        company = facade.getCompany(cvr);
        assertEquals(cvr, company.getCvr());
        assertEquals(zipCode, company.getAddress().getCityInfo().getZipCode());
    }

    @Test
    public void testGetCompany() throws NotFoundException, ExistException {
        Company c = facade.getCompany(CVR_3);
        assertEquals("Yelling Corp", c.getName());
        assertEquals(CVR_3, c.getCvr());
    }

    @Test
    public void testEditCompany() throws NotFoundException, ExistException {
        String name = "Edited company";
        String description = "edited description";
        int numEmployees = 35;
        long marketValue = 500l;
        String email = "edit@edited.dk";
        CityInfo cityInfo = new CityInfo("2500", "Valby");
        AddressId addressId = new AddressId("Edited address", "3.th.");
        Address address = new Address(addressId, cityInfo);
        address.setCityInfo(cityInfo);
        String phone1Descrip = "edited 1";
        String phone2Descrip = "edited 2";
        String phone3Descrip = "edited 3";
        Phone phone1 = new Phone("33333333", phone1Descrip);
        Phone phone2 = new Phone("66666666", phone2Descrip);
        Phone phone3 = new Phone("22222222", phone3Descrip);
        Phone phone4 = new Phone("26587213", "trying to add existing number");
        List<Phone> phones = new ArrayList();
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);
        phones.add(phone4);
        Company company = new Company(CVR_4, name, description, numEmployees, marketValue);
        company.setPhones(phones);
        company.setEmail(email);
        company.setAddress(address);
        assertEquals(name, company.getName());
        assertEquals(CVR_4, company.getCvr());
        facade.editCompany(company);
        company = facade.getCompany(CVR_4);
        assertEquals(CVR_4, company.getCvr());
        assertEquals(email, company.getEmail());
        assertEquals(name, company.getName());
        assertEquals(description, company.getDescription());
        assertEquals(numEmployees, company.getNumEmployees());
        assertEquals(marketValue, company.getMarketValue());

        assertEquals(address.getAddressId().getStreet(), company.getAddress().getAddressId().getStreet());
        assertEquals(address.getAddressId().getAdditionalInfo(), company.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(cityInfo.getZipCode(), company.getAddress().getCityInfo().getZipCode());
        assertEquals(cityInfo.getCity(), company.getAddress().getCityInfo().getCity());

        assertEquals(phones.size(), company.getPhones().size());
        int phoneCount = 0;
        for (Phone phone : phones) {
            for (Phone p : company.getPhones()) {
                if (p.getNumber().equals(phone.getNumber())) {
                    phoneCount++;
                }
            }
        }
        assertTrue(phoneCount == 4);

    }

    @Test 
    public void testDeleteCompany() throws NotFoundException {
        String name = "Drnk-And-Pizzed";
        Company company = facade.getCompany(CVR_0);
        assertEquals(company.getCvr(), CVR_0);
        assertEquals(company.getName(), name);
        Company deletedCompany = facade.deleteCompany(CVR_0);
        assertEquals(deletedCompany.getCvr(), CVR_0);
        assertEquals(deletedCompany.getName(), name);
    }
    
    @Test (expected = NotFoundException.class)
    public void testDeleteNotFoundCompany() throws NotFoundException {
        facade.deleteCompany(CVR_3);
        facade.getCompany(CVR_3);
    }
}

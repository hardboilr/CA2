package interfaces;

import entities.CityInfo;
import entities.Company;
//import exception.CompanyNotFoundException;
import java.util.List;

/**
 *
 * @author sebastiannielsen
 */
public interface ICompanyFacade {
    
    Company createCompany(Company c);
    Company getCompany(long cvr) throws Exception; 
    List<Company> getCompanies();
    List<Company> getCompaniesInCity(CityInfo city) throws Exception;
    List<Company> getCompaniesWithEmployeeCount(Long empCount) throws Exception;
    Company editCompany(Company c) throws Exception;
    Company deleteCompany(long cvr) throws Exception;
    
}

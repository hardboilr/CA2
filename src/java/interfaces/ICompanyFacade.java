package interfaces;

import entities.CityInfo;
import entities.Company;
import exception.NotFoundException;
import exception.ExistException;
import java.util.List;

public interface ICompanyFacade {

    Company createCompany(Company c) throws ExistException;

    Company getCompany(int cvr) throws NotFoundException;

    List<Company> getCompanies();

    List<Company> getCompaniesInCity(CityInfo city) throws Exception;

    List<Company> getCompaniesWithEmployeeCount(Long empCount) throws NotFoundException;

    List<Company> getCompaniesValuedMoreThan(Long value) throws Exception;

    Company editCompany(Company c) throws NotFoundException;

    Company deleteCompany(int cvr) throws NotFoundException;
}

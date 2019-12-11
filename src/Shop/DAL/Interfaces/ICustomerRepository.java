package Shop.DAL.Interfaces;

import Shop.DAL.Models.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICustomerRepository extends IGenericRepository<Customer>
{
    ResultSet GetCustomerByUserId(int id) throws SQLException;
}

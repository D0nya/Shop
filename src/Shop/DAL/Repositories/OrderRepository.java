package Shop.DAL.Repositories;

import Shop.DAL.Interfaces.IOrderRepository;
import Shop.DAL.Models.Customer;
import Shop.DAL.Models.Order;
import Shop.DAL.Models.Role;
import Shop.DAL.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends GenericRepository<Order> implements IOrderRepository
{
    public OrderRepository(String dbSet, Connection db)
    {
        super(dbSet, db);
    }

    @Override
    public ResultSet GetAll() throws SQLException
    {
        query = "SELECT orderid, orderstatus, totalprice, customers.customerId, customerName, phoneNumber, " +
                "users.userId, login, users.password, email, roles.roleId, roles.Name" +
                " FROM " + dbSet +
                " INNER JOIN Customers ON customers.customerId = orders.customerId" +
                " INNER JOIN Users ON users.userId = customers.userId" +
                " INNER JOIN Roles ON roles.roleId = users.roleId";
        Statement st = db.createStatement();
        return st.executeQuery(query);
    }

    @Override
    public ResultSet Get(int id) throws SQLException
    {
        query = "SELECT orderid, orderstatus, totalprice, customers.customerId, customerName, phoneNumber, " +
                "users.userId, login, users.password, email, roles.roleId, roles.Name" +
                " FROM " + dbSet +
                " INNER JOIN Customers ON customers.customerId = orders.customerId" +
                " INNER JOIN Users ON users.userId = customers.userId" +
                " INNER JOIN Roles ON roles.roleId = users.roleId" +
                " WHERE orderId = " + id;
        Statement st = db.createStatement();
        return st.executeQuery(query);
    }

    @Override
    public void Create(Order item) throws SQLException
    {
        query = "INSERT INTO " + dbSet + "(orderStatus, TotalPrice, CustomerId) VALUES (?,?,?)";
        PreparedStatement ps = db.prepareStatement(query);
        ps.setString(1, item.getStatus());
        ps.setDouble(2, item.getTotalPrice());
        ps.setInt(3, item.getCustomer().getId());
        ps.executeUpdate();
    }

    @Override
    public void Update(Order item) throws SQLException
    {
        query = "UPDATE " + dbSet + " SET OrderStatus = ?, TotalPrice = ?, CustomerId = ? WHERE OrderId = " + item.getId();
        PreparedStatement ps = db.prepareStatement(query);
        ps.setString(1, item.getStatus());
        ps.setDouble(2, item.getTotalPrice());
        ps.setInt(3, item.getCustomer().getId());
        ps.executeUpdate();
    }

    @Override
    public List<Order> ProcessData(ResultSet result) throws SQLException
    {
        List<Order> data = new ArrayList<>();
        while(result.next())
        {
            data.add(new Order(result.getInt(1), result.getString(2), result.getDouble(3),
                    new Customer(result.getInt(4), result.getString(5), result.getString(6),
                            new User(result.getInt(7), result.getString(8), result.getString(9), result.getString(10),
                                    new Role(result.getInt(11), result.getString(12)))),
                    new ArrayList<>()));
        }
        return data;
    }
}

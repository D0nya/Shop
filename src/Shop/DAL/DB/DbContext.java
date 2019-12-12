package Shop.DAL.DB;

import Shop.DAL.Models.*;
import Shop.DAL.UnitOfWork;

import java.sql.*;
import java.util.ArrayList;

public class DbContext
{
    private Connection connection;

    public DbContext(String connectionString, String user, String password) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, user, password);
    }

    public Connection getConnection()
    {
        return connection;
    }

    public void Initialize() throws SQLException {
        UnitOfWork uof = new UnitOfWork(connection);
        uof.getUsers().Create(new User(0, "matthew", "1311", "matthewZhorov@gmail.com", new Role(2, "user")));
        uof.getUsers().Create(new User(0, "yan", "yan", "yanchernyavsky@gmail.com", new Role(2, "user")));
        uof.getUsers().Create(new User(0, "vasili", "capslock", "vasiliPavluchenko@gmail.com", new Role(2, "user")));
        uof.getUsers().Create(new User(0,"d0nya", "qwerty", "dboeshko@gmail.com", new Role(1, "admin")));

        uof.getCustomers().Create(new Customer(0, "Матвей Жоров", "+375297736151", new User(3, "", "", "", null)));
        uof.getCustomers().Create(new Customer(0, "Ян Чернявский", "+375295037456", new User(4, "", "", "", null)));
        uof.getCustomers().Create(new Customer(0, "Василий Павлюченко", "+375445612063", new User(5, "", "", "", null)));
        uof.getCustomers().Create(new Customer(0, "Данила Боешко", "+375298239831", new User(6, "", "", "", null)));

        uof.getCategories().Create(new Category(0, "Смартфоны", "смартфоны", null));
        uof.getCategories().Create(new Category(0, "Планшеты", "планшеты", null));

        uof.getVendors().Create(new Vendor(0, "Apple", "apple@apple.com", "1-800-MY-APPLE", "US"));
        uof.getVendors().Create(new Vendor(0, "Samsung", "samsung@samsung.com", "0-800-502-000", "Юж. Корея"));

        uof.getProducts().Create(new Product(0, "iPhone", "Характеристики....", 1000, new Vendor(1, "", "","",""), null));
        uof.getProducts().Create(new Product(0, "iPad", "Характеристики....", 1300, new Vendor(1, "", "","",""),null ));
        uof.getProducts().Create(new Product(0, "Samsung Galaxy", "Характеристики....", 800, new Vendor(2, "", "","",""), null));
    }
}

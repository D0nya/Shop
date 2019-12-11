package Shop.DAL.Models;

import java.util.ArrayList;

public class Order
{
    private int id;
    private Customer customer;
    private String status;
    private double totalPrice;
    private ArrayList<Category_Product> products;

    public Order()
    {

    }

    public Order(int id, String status, double totalPrice, Customer customer, ArrayList<Category_Product> products)
    {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Category_Product> getProducts()
    {
        return products;
    }

    public void setProducts(ArrayList<Category_Product> products)
    {
        this.products = products;
    }
}

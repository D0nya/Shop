package Shop.DAL.Models;

public class Customer
{
    private int id;
    private String name;
    private String phoneNumber;
    private User user;

    public Customer()
    {

    }
    public Customer(int id, String name, String phoneNumber, User user)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}

//Student ID: u3185025
public class Customer //Stores all customer data
{
    String code;
    String name;
    String contact;
    String address;
    public Customer()
    {
    }
    
    public Customer(String code, String name, String contact, String address)
    {
    this.code = code;
    this.name = name;
    this.contact = contact;
    this.address = address;
    }
    
    public Customer(Customer input)
    {
    code = input.code;
    name = input.name;
    contact = input.contact;
    address = input.address;
    }
    
    public String getCode()
    {
    return code;
    }
    
    public String getName()
    {
    return name;
    }
    
    public String getContact()
    {
    return contact;
    }
    
    public String getAddress()
    {
    return address;
    }

}

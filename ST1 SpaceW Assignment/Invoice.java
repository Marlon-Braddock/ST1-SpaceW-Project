//Student ID: u3185025
public class Invoice implements Comparable<Invoice> //Stores all data about a single launch
{
    private String service = "NONE";
    private Date date = new Date();
    private String orbit = "NONE";
    private int payload = 0;
    private String site = "NONE";
    private boolean gw;
    private Cost cost = new Cost();
    private Customer customer = new Customer();
    

    public Invoice()
    {
    }

    public Invoice(Date date, Customer customer, String service, String orbit, String payload,
    String site, boolean gw) //Constructor used when scanning file
    {
        this.date = new Date(date);
        this.customer = customer;
        service = service.trim().toUpperCase();
        switch(service)
        {
            case"ORBL":
            case"ORBLH":
            case"ORBC1U":
            case"ORBC2U":
            case"ORBC3U":
            case"ORBC6U":
            this.service = service;
            break;
            default:
            this.service = "ERROR";
            break;
        }
        orbit = orbit.trim().toUpperCase();
        if (orbit.equals("GTO") || orbit.equals("LEO"))
        {
            this.orbit = orbit;
        }
        else
        {
            this.orbit = "ERROR";
        }
        if(Function.intParseCheck(payload))
        {
            this.payload = Integer.parseInt(payload);
        }
        else
        {
            this.payload = -1;
        }
        site = site.trim().toUpperCase();
        if (site.equals("LC40") || site.equals("KSC"))
        {
            this.site = site;
        }
        else
        {
            this.site = "ERROR";
        }
        this.gw = gw;
    }

    public void setService(String input)
    {
        input = input.toUpperCase();
        switch(input)
        {
            case"ORBL":
            case"ORBLH":
            case"ORBC1U":
            case"ORBC2U":
            case"ORBC3U":
            case"ORBC6U":
            service = input;
            break;
            default:
            service = "ERROR";
            break;
        }
    }

    public void setOrbit(String input)
    {
        input = input.toUpperCase();
        if (input.equals("GTO") || input.equals("LEO"))
        {
            orbit = input;
        }
        else
        {
            orbit = "ERROR";
        }
    }

    public void setPayload(String input)
    {
        int input2;
        if(Function.intParseCheck(input))
        {
            input2 = Integer.parseInt(input);
            if (input2 < 10000)
            {
                payload = -1;
            }
            else if (input2 > 900000000)
            {
                payload = 1;
            }
            else
            {
                payload = input2;
            }
        }
        else
        {
            payload = -2;
        }
    }

    public void setSite(String input)
    {
        input = input.toUpperCase();
        switch(input)
        {
            case"LC40":
            case"KSC":
            site = input;
            break;
            default:
            site = "ERROR";
        }
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public String getService()
    {
        return service;
    }

    public String getOrbit()
    {
        return orbit;
    }

    public int getPayload()
    {
        return payload;
    }

    public void setGw(boolean input)
    {
        gw = input;
    }

    public String getSite()
    {
        return site;
    }

    public boolean getGw()
    {
        return gw;
    }

    public void setDate(Date input)
    {
        date = new Date(input);
    }

    public void setCustomer(Customer input)
    {
        customer = new Customer(input);
    }

    public String getInvoiceDate()
    {
        return date.getDate();
    }

    public void calculateCost() //Creates a new cost object which calculates and stores all costs
    {
        cost = new Cost(this);
    }

    public Cost getCost()
    {
        return cost;
    }
    
    @Override
    public int compareTo(Invoice o) //Compares dates between invoice objects
    {
        return this.getInvoiceDate().compareTo(o.getInvoiceDate());
    }
}

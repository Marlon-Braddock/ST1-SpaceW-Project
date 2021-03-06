import java.util.*;
//Student ID: u3185025
public class CustomerList //Used to store and access customer objects
{
    ArrayList<Customer> customerList = new ArrayList<Customer>();
    public CustomerList()
    {
        Customer ESSA = new Customer("ESAA", "Europe Systems Alternative Agency","Jean-Claude Junxer", 
                "23 Razor Road Belconnen ACT, 2617");
        customerList.add(ESSA);
        Customer NASHA = new Customer("NASHA", "National Air Space Hash Agency", "Jimmy Briden", 
                "2 Mashup Drive Bruce ACT");
        customerList.add(NASHA);
        Customer ASA  = new Customer("ASA", "Aussie Space Agency", "Megan Clock", 
                "Flat 31/a, Bax Units Stix St, Marble Bar, WA 6760");
        customerList.add(ASA );
        Customer TICK  = new Customer("TICK", "Tick Incorporated", "Mark Watson",
                "87 Race Drive Bathurst, NSW 2795");
        customerList.add(TICK );
        Customer BINC   = new Customer("BINC", "Byer Private Space Incorporated", "Marilyn Hewson",
                "212 Webly Drive, Canowindra, NSW 2804");
        customerList.add(BINC );
        Customer CODC  = new Customer("CODC", "Corporate Space Trust", "Zhang Chen",
                "212 Scorch Drive, Belanta, SA 5730");
        customerList.add(CODC );
        Customer MARZ  = new Customer("MARZ", "The Mars Gen Inc", "Cool Dude",
                "26 O'Conner's Road, Werribee South, VIC 3030");
        customerList.add(MARZ );
    }
}

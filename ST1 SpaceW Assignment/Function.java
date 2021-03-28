import java.util.*;
import java.util.regex.*;
//Student ID: u3185025
//Contains resued functions
public class Function
{
    private static Pattern format = Pattern.compile("\\d{2}/\\d{2}/\\d{2}"); //Used to check date format
    public Function()
    {
    }

    public static boolean intParseCheck(String input) 
    //Used to check if string is can be parsed into integer
    {
        try 
        {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean checkDateFormat(String input)
    {
        return format.matcher(input).matches();
    }

    public static void printSummary(Invoice input) //Used in one off invoice
    {
        String line = "-";
        System.out.printf("\n%60s","SpaceW Invoice Summary");
        System.out.printf("\n%60s",line.repeat(22));
        System.out.printf("\n%-14s:%14s","Launch Date",input.getInvoiceDate());
        System.out.printf("\n%-14s:%14s","Client Code",input.getCustomer().getCode());
        System.out.printf("\n%-14s:%14s","Orbit",input.getOrbit());
        System.out.printf("\n%-14s:%14s","Launch Site",input.getSite());
        System.out.printf("\n%-14s:$%,13d","Payload Value",input.getPayload());
        if(input.getGw())
        {
            System.out.printf("\n%-14s:%14s","GW Monitor","Yes");
        }
    }

    public static void printInvoice(ArrayList<Invoice> input) //Prints monthly invoice
    {
        String line = "-";
        int totalCost = 0;
        System.out.printf("\n%60s","SpaceW Invoice");
        System.out.printf("\n%60s",line.repeat(14));
        System.out.printf("\n%-12s: %s","To",input.get(0).getCustomer().getName());
        System.out.printf("\n%-12s: %s","Attention",input.get(0).getCustomer().getContact());
        System.out.printf("\n%-12s: %s\n","Client Code",input.get(0).getCustomer().getCode());
        System.out.printf("\n%s",line.repeat(106));
        System.out.printf("\n|%14s|%14s|%14s|%14s|%14s|%14s|%14s|","Launch","Tracking","Site","GW",
            "Service", "Service","Total");
        System.out.printf("\n|%14s|%14s|%14s|%14s|%14s|%14s|%14s|","Date","Fee","Fee","Tax",
            "", "Cost","");
        System.out.printf("\n|%14s|%14s|%14s|%14s|%14s|%14s|%14s|",line.repeat(13),line.repeat(13),
            line.repeat(13),line.repeat(13),line.repeat(13),line.repeat(13),line.repeat(13));
        for(int i = 0; i < input.size(); i++) 
        {
            System.out.printf("\n|%14s|$%,13d|$%,13d|$%,13d|%14s|$%,13d|$%,13d|",input.get(i).getInvoiceDate(),
                input.get(i).getCost().getTrackFee(),input.get(i).getCost().getSiteFee(),input.get(i).getCost().getTax(),
                input.get(i).getService(),input.get(i).getCost().getLaunchCost(),input.get(i).getCost().getTotal());
            totalCost += input.get(i).getCost().getTotal();

            if(input.get(i).getOrbit().equals("ERROR"))
            {
                System.out.println("<ERROR>: Orbit not found");
            }
            if(input.get(i).getCustomer().getCode() == null)
            {
                System.out.println("<ERROR>: Customer not found");
            }
            if(input.get(i).getService().equals("ERROR"))
            {
                System.out.println("<ERROR>: Service not found");
            }
            if(input.get(i).getSite().equals("ERROR"))
            {
                System.out.println("<ERROR>: Site not found");
            }
            if(input.get(i).getPayload() < 1)
            {
                System.out.println("<ERROR>: Payload not found");
            }
            if(input.get(i).getInvoiceDate().equals("ERROR"))
            {
                System.out.println("<ERROR>: Date not found");
            }
            if(!input.get(0).getCustomer().getCode().equals(input.get(i).getCustomer().getCode()))
            {
                System.out.println("<ERROR>: Differnet customer codes detected");
            }
        }
        System.out.printf("\n|%90s%s|","|",line.repeat(14));
        System.out.printf("\n|%90s$%,13d|","|",totalCost);
        System.out.printf("\n%s\n",line.repeat(106));
    }

    public static void printSchedule(ArrayList<Invoice> input, boolean sort) 
    //Prints either an unsorted or sorted launch schedule depending on boolean input
    {
        String line = "-";
        String line2 = line.repeat(9);
        System.out.printf("\n%60s","SpaceW Launch Schedule");
        System.out.printf("\n%60s",line.repeat(22));
        System.out.printf("\n|%-9s|%-9s|%-9s|%-9s|","Date","Client","Service","LaunchPad");
        System.out.printf("\n|%9s|%9s|%9s|%9s|",line2,line2,line2,line2);
        if(!sort)//Unsorted
        {
            for(int i = 0; i < input.size(); i++)
            {
                System.out.printf("\n|%9s|%9s|%9s|%9s|",input.get(i).getInvoiceDate(),input.get(i).getCustomer().getCode(),
                    input.get(i).getService(), input.get(i).getSite());
                if(input.get(i).getOrbit().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Orbit not found");
                }
                if(input.get(i).getCustomer().getCode() == null)
                {
                    System.out.println("<ERROR>: Customer not found");
                }
                if(input.get(i).getService().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Service not found");
                }
                if(input.get(i).getSite().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Site not found");
                }
                if(input.get(i).getPayload() < 1)
                {
                    System.out.println("<ERROR>: Payload not found");
                }
                if(input.get(i).getInvoiceDate().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Date not found");
                }
            }
        }
        else//Sorted
        {
            ArrayList<Invoice> ksc = new ArrayList<Invoice>();
            ArrayList<Invoice> lc40 = new ArrayList<Invoice>();
            ArrayList<Invoice> temp = new ArrayList<Invoice>();

            for(int i = 0; i < input.size(); i++)
            {
                if(input.get(i).getSite().equals("KSC"))
                {
                    ksc.add(input.get(i));
                }
                else
                {
                    lc40.add(input.get(i));
                }
            }
            Collections.sort(ksc);
            Collections.sort(lc40);

            for(int i = 0; i < ksc.size(); i++)
            {
                System.out.printf("\n|%9s|%9s|%9s|%9s|",ksc.get(i).getInvoiceDate(),ksc.get(i).getCustomer().getCode(),
                    ksc.get(i).getService(), ksc.get(i).getSite());

                if(ksc.get(i).getOrbit().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Orbit not found");
                }
                if(ksc.get(i).getCustomer().getCode() == null)
                {
                    System.out.println("<ERROR>: Customer not found");
                }
                if(ksc.get(i).getService().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Service not found");
                }
                if(ksc.get(i).getSite().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Site not found");
                }
                if(ksc.get(i).getPayload() < 1)
                {
                    System.out.println("<ERROR>: Payload not found");
                }
                if(ksc.get(i).getInvoiceDate().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Date not found");
                }
            }
            System.out.printf("\n|%9s|%9s|%9s|%9s|",line2,line2,line2,line2);
            for(int i = 0; i < lc40.size(); i++)
            {
                System.out.printf("\n|%9s|%9s|%9s|%9s|",lc40.get(i).getInvoiceDate(),lc40.get(i).getCustomer().getCode(),
                    lc40.get(i).getService(), lc40.get(i).getSite());

                if(lc40.get(i).getOrbit().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Orbit not found");
                }
                if(lc40.get(i).getCustomer().getCode() == null)
                {
                    System.out.println("<ERROR>: Customer not found");
                }
                if(lc40.get(i).getService().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Service not found");
                }
                if(lc40.get(i).getSite().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Site not found");
                }
                if(lc40.get(i).getPayload() < 1)
                {
                    System.out.println("<ERROR>: Payload not found");
                }
                if(lc40.get(i).getInvoiceDate().equals("ERROR"))
                {
                    System.out.println("<ERROR>: Date not found");
                }
            }
        }
    }
}

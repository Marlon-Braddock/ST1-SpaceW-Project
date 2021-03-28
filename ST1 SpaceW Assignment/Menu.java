import java.util.*;
import java.io.*;
import java.io.FileReader;
//Student ID: u3185025
//This class contains all main functions, called by main class
public class Menu
{
    static CustomerList c = new CustomerList();
    public static final ArrayList<Customer> customerList = c.customerList; 
    //Array list to store customer details

    public static int mainMenu() //Main menu function
    {
        String title = "SpaceW Terminal";
        String mMenu = "Main Menu";
        String line = "-";
        System.out.printf("\f\n");
        System.out.printf("%60s\n", title);
        System.out.printf("%60s\n",line.repeat(title.length()));
        System.out.printf("\n%s\n", mMenu);
        System.out.printf("%s\n", line.repeat(mMenu.length()));
        System.out.println("1: Create Invoice");
        System.out.println("2: Monthy Invoice");
        System.out.println("3: Launch Schedule");
        System.out.println("4: Sorted Launch Schedule");
        System.out.println("5: Exit\n");
        System.out.println("Select Option: ");

        Scanner in = new Scanner(System.in);
        boolean inputLoop = true;
        String input;
        int input2 = 0;

        while(inputLoop)
        {
            input = in.nextLine().trim();
            if(Function.intParseCheck(input))
            {
                input2 = Integer.parseInt(input);
                if(input2 > 0 && input2 < 6)
                {
                    inputLoop = false;
                }
                else
                {
                    System.out.println("Invalid input");
                }
            }

            else
            {
                System.out.println("Invalid input");
            }
        }
        return input2;
    }

    public static void createInvoice() //One off invoice creation
    {
        Invoice invoice = new Invoice();
        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>(); //Stores invoice
        boolean menuLoop = true;

        while(menuLoop)
        {
            boolean inputLoop = true;
            String title = "Invoice Creation";
            String line = "-";
            System.out.printf("\n%s", title);
            System.out.printf("\n%s", line.repeat(title.length()));
            System.out.println("\nEnter Date(dd/mm/yy): ");

            Scanner in = new Scanner(System.in);
            while(inputLoop)
            {
                Date tempDate = new Date();
                String input = in.nextLine().trim();
                if (Function.checkDateFormat(input))
                {
                    String[] tempInput = input.split("/");
                    Date inputDate = new Date(Integer.parseInt(tempInput[0]), 
                            Integer.parseInt(tempInput[1]), Integer.parseInt(tempInput[2]));
                    if(inputDate.getDay() > 0 && inputDate.getMonth() > 0 
                    && inputDate.getYear() > 0)
                    {
                        invoice.setDate(inputDate);
                        inputLoop = false;
                    }
                    else
                    {
                        System.out.println("Invalid input");
                        System.out.println("Enter Date:");
                    }
                }
                else
                {
                    System.out.println("Invalid input");
                    System.out.println("Enter Date:");
                }
            }
            inputLoop = true;

            loop:while(inputLoop)
            {
                System.out.println("Enter Customer Code: ");
                String input = in.nextLine().trim().toUpperCase();
                for(int i = 0; i < customerList.size(); i++) //Scans customerList for customer code
                {
                    if(customerList.get(i).code.equals(input)) 
                    {
                        invoice.setCustomer(customerList.get(i));
                        inputLoop = false;
                        break loop;
                    }
                }
                System.out.println("Customer not found");
            }
            inputLoop = true;

            while(inputLoop)
            {
                System.out.println("Enter Service Code: ");
                String input = in.nextLine().trim().toUpperCase();
                invoice.setService(input);
                if(invoice.getService().equals("ERROR") || invoice.getService().equals("NONE"))
                {
                    System.out.println("Invalid Input");
                }
                else
                {
                    inputLoop = false;
                }
            }

            inputLoop = true;
            while(inputLoop)
            {
                System.out.println("Enter Orbit Type: ");
                String input = in.nextLine().trim().toUpperCase();
                invoice.setOrbit(input);
                if(invoice.getOrbit().equals("ERROR") || invoice.getService().equals("NONE"))
                {
                    System.out.println("Invalid Input");
                }
                else
                {
                    inputLoop = false;
                }
            }

            inputLoop = true;
            while(inputLoop)
            {
                System.out.println("Enter Payload Value: ");
                String input = in.nextLine().trim().toUpperCase();
                invoice.setPayload(input);

                switch(invoice.getPayload())
                {
                    case -2:
                    System.out.println("Invalid Input");
                    break;
                    case -1:
                    System.out.println("Value too small");
                    break;
                    case 1:
                    System.out.println("Value too great");
                    break;
                    default:
                    inputLoop = false;
                    break;
                }
            }

            if(invoice.getPayload() > 1000000)
            {
                boolean loop = true;
                while(loop)
                {
                    System.out.println("Is payload a Global Warming Monitor Y/N?: ");
                    String input = in.nextLine().trim().toUpperCase();
                    switch(input)
                    {
                        case"Y":
                        case"YES":
                        invoice.setGw(true);
                        loop = false;
                        break;
                        case"N":
                        case"NO":
                        invoice.setGw(false);
                        loop = false;
                        break;
                        default:
                        System.out.println("Invalid Input");
                        break;
                    }
                }
            }

            inputLoop = true;
            while(inputLoop)
            {
                System.out.println("Enter Launch Site:");
                String input = in.nextLine().trim().toUpperCase();
                invoice.setSite(input);
                if(invoice.getSite().equals("ERROR"))
                {
                    System.out.println("Invalid Input");
                }
                else
                {
                    inputLoop = false;
                }
            }

            inputLoop = true;

            invoice.calculateCost(); //Updates cost based on input values
            invoiceList.add(invoice);
            Function.printSummary(invoice); //Prints invoice

            Function.printInvoice(invoiceList);
            while(inputLoop)
            {
                System.out.printf("\n\n1. Main Menu\n2. Exit\n");
                String input = in.nextLine().trim().toUpperCase();
                if(Function.intParseCheck(input))
                {
                    int i = Integer.parseInt(input);
                    switch(i)
                    {
                        case 1:
                        menuLoop = false;
                        inputLoop = false;
                        break;
                        case 2:
                        Main.programLoop = false;
                        menuLoop = false;
                        inputLoop = false;
                        break;
                    }
                }
                else
                {
                    System.out.println("Invalid Input");
                }
            }
        }
    }

    public static void monthlyInvoice(int option, boolean sort) 
    //Scans file and prints either a monthly invoice, unsorted launch schedule or sorted launch schedule
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>(); //Stores all created invoice objects
        boolean inputLoop = true;
        boolean menuLoop = true;

        while(menuLoop)
        {
            while(inputLoop)
            { 
                System.out.println("File to process: ");
                String file = in.nextLine();
                try
                {
                    Scanner inFile = new Scanner(new FileReader(file)); //Used to scan file
                    while(inFile.hasNextLine())
                    {
                        String line = inFile.nextLine();
                        String[] content = {"","","","","","",""}; 
                        //Premade array in case not enough strings from file
                        String[] fileLine = line.split("\\s*,\\s*"); //Array of split strings from file
                        for(int i = 0; i < fileLine.length; i++) 
                        //Loop fills premade array with scanned strings
                        {
                            content[i] = fileLine[i];
                        }
                        Date inputDate = new Date(); 
                        //Premade objects to fill, made in case not enough data to create
                        Customer customer = new Customer();

                        boolean gw = false;

                        if (Function.checkDateFormat(content[0])) //For scanned date
                        {
                            String[] tempInput = content[0].split("/");
                            inputDate = new Date(Integer.parseInt(tempInput[0]), 
                                Integer.parseInt(tempInput[1]), Integer.parseInt(tempInput[2]));
                        }
                        for(int i = 0; i < customerList.size(); i++)//Gets customer
                        {
                            if(customerList.get(i).code.equals(content[1]))
                            {
                                customer = new Customer(customerList.get(i));
                            }
                        }
                        if(content[6].trim().toUpperCase().equals("Y"))
                        {
                            gw = true;
                        }
                        Invoice invoice = new Invoice(inputDate, customer, content[2], content[3],
                                content[4], content[5], gw);
                        invoice.calculateCost();
                        invoiceList.add(invoice);
                    }
                    inFile.close();
                    inputLoop = false;
                    switch(option) 
                    //Chooses method depending on if monthly invoice, schedule or sorted schedule are inputed
                    {
                        case 0:
                        Function.printInvoice(invoiceList);
                        break;
                        case 1:
                        Function.printSchedule(invoiceList, sort);
                        break;
                        default:
                        System.out.println("Internal Error");
                        break;
                    }
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("File Not found");
                }

            }
            inputLoop = true;
            while(inputLoop)
            {
                System.out.printf("\n\n1. Main Menu\n2. Exit\n");
                String input = in.nextLine().trim().toUpperCase();
                if(Function.intParseCheck(input))
                {
                    int i = Integer.parseInt(input);
                    switch(i)
                    {
                        case 1:
                        menuLoop = false;
                        inputLoop = false;
                        break;
                        case 2:
                        Main.programLoop = false;
                        menuLoop = false;
                        inputLoop = false;
                        break;
                    }
                }
                else
                {
                    System.out.println("Invalid Input");
                }
            }
        }

    }
}
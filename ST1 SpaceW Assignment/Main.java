//Student ID: u3185025 
//Main file
public class Main
{
    public static boolean programLoop = true; //Used to determine when user has selected exit
    public Main()
    {
        programLoop = true;
        loop:while (programLoop)
        {
            int option = Menu.mainMenu(); //Main menu method, returns int based on input
            switch (option)
            {
                case 1:
                Menu.createInvoice();
                break;
                case 2:
                Menu.monthlyInvoice(0, false);
                break;
                case 3:
                Menu.monthlyInvoice(1, false);
                break;
                case 4:
                Menu.monthlyInvoice(1, true);
                break;
                case 5:
                programLoop = false;
                break;
                default: System.out.println("INTERNAL ERROR");
            }
        }
        System.out.println("*-----<Program Terminated>-----*");
    }
}

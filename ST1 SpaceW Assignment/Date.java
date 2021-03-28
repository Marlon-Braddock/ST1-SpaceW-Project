//Student ID: u3185025
public class Date //Simple date classs
{
    private int day;
    private int month;
    private int year;
    private boolean leapYear;
    private int dayLimit;

    public Date(int day, int month, int year)
    {
        if (year > 0 && year < 100) //Checks if leap year
        {
            if (year % 4 != 0) 
            {
                this.leapYear = false;
            } 
            else if (year % 400 == 0) 
            {
                this.leapYear =  true;
            } 
            else if (year % 100 == 0) 
            {
                this.leapYear =  false;
            } 
            else 
            {
                this.leapYear =  true;
            }
            this.year = year;
        }
        else
        {
            year = -1;
        }

        if(month > 0 && month < 13)
        {
            switch(month)
            {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                this.dayLimit = 32;
                this.month = month;
                break;
                case 4:
                case 6:
                case 9:
                case 11:
                dayLimit = 31;
                this.month = month;
                break;
                case 2:
                if (this.leapYear)
                {
                    this.dayLimit = 30;
                }
                else
                {
                    this.dayLimit = 29;
                }
                this.month = month;
                break;
                default:
                this.month = -1;
            }
        }
        else
        {
            this.month = -1;
        }

        if (day > 0 && day < this.dayLimit)
        {
            this.day = day;
        }
        else
        {
            this.day = -1;
        }
    }

    public Date()
    {
    }

    public Date(Date input)
    {
        if (input.year > 0 && input.year < 100)
        {
            if (input.year % 4 != 0) 
            {
                leapYear = false;
            } 
            else if (input.year % 400 == 0) 
            {
                leapYear =  true;
            } 
            else if (input.year % 100 == 0) 
            {
                leapYear =  false;
            } 
            else 
            {
                leapYear =  true;
            }
            year = input.year;
        }
        else
        {
            year = -1;
        }

        if(input.month > 0 && input.month < 13)
        {
            switch(input.month)
            {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                dayLimit = 32;
                month = input.month;
                break;
                case 4:
                case 6:
                case 9:
                case 11:
                dayLimit = 31;
                month = input.month;
                break;
                case 2:
                if (leapYear)
                {
                    dayLimit = 30;
                }
                else
                {
                    dayLimit = 29;
                }
                month = input.month;
                break;
                default:
                month = -1;
            }
        }
        else
        {
            month = -1;
        }

        if (input.day > 0 && input.day < dayLimit)
        {
            day = input.day;
        }
        else
        {
            day = -1;
        }
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public String getDate()
    {
        if(day < 1 || month < 1 || year < 1)
        {
            return "ERROR";
        }
        else{
            return String.format("%02d/%02d/%02d",day,month,year);
        }
    }

}

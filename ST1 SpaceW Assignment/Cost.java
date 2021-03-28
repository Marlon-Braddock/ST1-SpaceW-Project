//Student ID: u3185025
public class Cost //Stores and calculates all costs related to a launch
{
    int launchCost;
    int tax;
    int trackingFee;
    int siteFee;
    int totalCost;

    public Cost()
    {
    }

    public Cost(Invoice input)
    {
        String service = input.getService();
        String site = input.getSite();
        int payload = input.getPayload();

        switch(service)
        {
            case"ORBL":
            launchCost = 62000000;
            break;
            case"ORBLH":
            launchCost = 90000000;
            break;
            case"ORBC1U":
            launchCost = 500000;
            break;
            case"ORBC2U":
            launchCost = 1000000;
            break;
            case"ORBC3U":
            launchCost = 1400000;
            break;
            case"ORBC6U":
            launchCost = 2000000;
            break;
            default:
            launchCost = -1;
            break;
        }

        if (input.getGw() && payload > 1000000)
        {
            tax = (int) Math.round(((double) payload * 0.13));
        }
        else
        {
            tax = 0;
        }

        if(input.getOrbit().equals("GTO"))
        {
            trackingFee = 120000;
        }
        else
        {
            trackingFee = 0;
        }

        if(input.getSite().equals("KSC"))
        {
            siteFee = 130000;
        }
        else
        {
            siteFee = 0;
        }

        totalCost = (launchCost + siteFee + tax + trackingFee);
    }

    public Cost(Cost input)
    {
        launchCost = input.launchCost;
        tax = input.tax;
        trackingFee = input.trackingFee;
        siteFee = input.siteFee;
        totalCost = (launchCost + siteFee + tax + trackingFee);
    }

    public int getLaunchCost()
    {
        return launchCost;
    }

    public int getTax()
    {
        return tax;
    }

    public int getTrackFee()
    {
        return trackingFee;
    }

    public int getSiteFee()
    {
        return siteFee;
    }

    public int getTotal()
    {
        return totalCost;
    }

    public void updateTotal()
    {
        totalCost = (launchCost + siteFee + tax + trackingFee);
    }
}
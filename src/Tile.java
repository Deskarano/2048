public class Tile
{
    private Square mainSquare;
    private boolean containsSquare;

    public Tile()
    {
        containsSquare = false;
        mainSquare = new Square();
    }

    public boolean isFilled()
    {
        return containsSquare;
    }

    public void generateSquare()
    {
        mainSquare = new Square();
        containsSquare = true;
    }

    public void deleteSquare()
    {
        // mainSquare will just be overwritten the next time the tile is
        // accessed
        containsSquare = false;
    }

    public void incrementSquare()
    {
        mainSquare.incrementExponent();
    }

    public void setSquare(int exponent)
    {
        mainSquare.setExponent(exponent);

        if(exponent == 0)
        {
            containsSquare = false;
        }
        else
        {
            containsSquare = true;
        }
    }

    public int getSquare()
    {
        if(!containsSquare)
        {
            return 0;
        }
        else
        {
            return mainSquare.getExponent();
        }
    }
}
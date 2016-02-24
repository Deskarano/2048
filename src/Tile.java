import java.util.Random;

public class Tile
{
    private int exponent;

    private static Random randGen = new Random();

    public Tile()
    {
        exponent = 0;
    }

    public boolean isFilled()
    {
        return exponent != 0;
    }

    public void generateSquare()
    {
        randGen.setSeed(System.nanoTime());

        if(randGen.nextInt(10) == 0)
        {
            exponent = 2;
        }
        else
        {
            exponent = 1;
        }
    }

    public void deleteSquare()
    {
        exponent = 0;
    }

    public void incrementSquare()
    {
        exponent++;
    }

    public void setSquare(int exp)
    {
        exponent = exp;
    }

    public int getSquare()
    {
        return exponent;
    }
}
public class Tile
{
    private Square mainSquare;
    private boolean containsSquare;
    
    public Tile()
    {
        System.out.println("initialized tile");
        containsSquare = false;
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
        //mainSquare will just be overwritten the next time the tile is accessed
        containsSquare = false;
    }
    
    public void incrementSquare()
    {
        mainSquare.incrementExponent();
    }
    
    public int getValue()
    {
        if(!containsSquare)
        {
            return 0;
        }
        else
        {
            return (int) Math.pow(2, mainSquare.getExponent());
        }
    }
}
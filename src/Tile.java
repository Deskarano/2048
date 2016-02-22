public class Tile
{
	private Square mainSquare;
	private boolean containsSquare;
	
	public Tile()
	{
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
		//TODO: this might cause issues
		mainSquare = null;
		containsSquare = false;
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
public class Tile
{
	private Square containedSquare;
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
		containedSquare = new Square();
	}
}

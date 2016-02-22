public class Board
{
	private Tile[][] currentState;
	
	private int height;
	private int width;
	
	public Board(int height, int width)
	{
		this.height = height;
		this.width = width;
		
		currentState = new Tile[height][width];
	}
	
	public void shiftLeft()
	{
		
	}

	public void shiftRight()
	{
		
	}
	
	public void shiftUp()
	{
		
	}
	
	public void shiftDown()
	{
		
	}
	
	public boolean gameOver()
	{
		//basic case: if there is an unfilled tile, the game is not over
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(!currentState[i][j].isFilled())
				{
					return false;
				}
			}
		}
		
		//TODO: check if any more merges are possible
		return false;
	}
}

import java.util.Random;

public class Board
{
	private Tile[][] currentState;

	private int height;
	private int width;
	
	private static Random randGen = new Random();

	public Board(int height, int width)
	{
		this.height = height;
		this.width = width;

		currentState = new Tile[height][width];
		
		generateNewSquare();
		generateNewSquare();
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
	
	private boolean shiftLeftPossible()
	{

	}

	private boolean shiftRightPossible()
	{

	}

	private boolean shiftUpPossible()
	{

	}

	private boolean shiftDownPossible()
	{

	}
	
	private boolean generateNewSquare()
	{
		randGen.setSeed(System.nanoTime());
		
		int h, w;
		
		while(true)
		{
			h = randGen.nextInt(height);
			w = randGen.nextInt(width);
			
			if(!currentState[h][w].isFilled())
			{
				currentState[h][w].generateSquare();
				return true;
			}
		}
	}

	public boolean gameOver()
	{
		// basic case: if there is an unfilled tile, the game is not over
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

		// TODO: check if any more merges are possible
		return false;
	}
}
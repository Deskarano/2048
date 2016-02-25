import java.util.Random;

public class Board
{
	private int[][] currentState;
	private int height;
	private int width;
	private int score;
	private int numMoves;

	private static Random randGen = new Random();

	public static final String LEFT = "l";
	public static final String RIGHT = "r";
	public static final String UP = "u";
	public static final String DOWN = "d";

	public static final int PROBABILITY = 10;

	public Board(int h, int w)
	{
		height = h;
		width = w;

		score = 0;

		currentState = new int[height][width];

		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				currentState[i][j] = 0;
			}
		}

		generateNewSquare();
		generateNewSquare();
	}

	public Board(int[][] startState)
	{
		height = startState.length;
		width = startState[0].length;

		score = 0;

		currentState = new int[height][width];

		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				currentState[i][j] = startState[i][j];
			}
		}
	}

	public void shift(String command)
	{
		if(command.equals(LEFT) & leftIsPossible())
		{
			numMoves++;
			
			shiftLeft();
			mergeLeft();
			shiftLeft();

			generateNewSquare();
		}

		else if(command.equals(RIGHT) & rightIsPossible())
		{
			numMoves++;
			
			shiftRight();
			mergeRight();
			shiftRight();

			generateNewSquare();
		}

		else if(command.equals(UP) & upIsPossible())
		{
			numMoves++;
			
			shiftUp();
			mergeUp();
			shiftUp();

			generateNewSquare();
		}

		else if(command.equals(DOWN) & downIsPossible())
		{
			numMoves++;
			
			shiftDown();
			mergeDown();
			shiftDown();

			generateNewSquare();
		}

		else
		{
			// do nothing
		}
	}

	private void shiftLeft()
	{
		int shiftPos = -1;

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = c; i >= 0; i--)
					{
						if(!isFilled(r, i))
						{
							shiftPos = i;
						}
					}

					if(shiftPos != -1)
					{
						currentState[r][shiftPos] = currentState[r][c];
						currentState[r][c] = 0;
					}
				}

				shiftPos = -1;
			}
		}
	}

	private void shiftRight()
	{
		int shiftPos = -1;

		for(int r = 0; r < height; r++)
		{
			for(int c = width - 1; c >= 0; c--)
			{
				if(isFilled(r, c))
				{
					for(int i = c; i < width; i++)
					{
						if(!isFilled(r, i))
						{
							shiftPos = i;
						}
					}

					if(shiftPos != -1)
					{
						currentState[r][shiftPos] = currentState[r][c];
						currentState[r][c] = 0;
					}
				}

				shiftPos = -1;
			}
		}
	}

	private void shiftUp()
	{
		int shiftPos = -1;

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = r; i >= 0; i--)
					{
						if(!isFilled(i, c))
						{
							shiftPos = i;
						}
					}

					if(shiftPos != -1)
					{
						currentState[shiftPos][c] = currentState[r][c];
						currentState[r][c] = 0;
					}
				}

				shiftPos = -1;
			}
		}
	}

	private void shiftDown()
	{
		int shiftPos = -1;

		for(int r = height - 1; r >= 0; r--)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = r; i < height; i++)
					{
						if(!isFilled(i, c))
						{
							shiftPos = i;
						}
					}

					if(shiftPos != -1)
					{
						currentState[shiftPos][c] = currentState[r][c];
						currentState[r][c] = 0;
					}
				}

				shiftPos = -1;
			}
		}
	}

	private void mergeLeft()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r][c + 1])
						{
							currentState[r][c]++;
							currentState[r][c + 1] = 0;

							score += currentState[r][c];
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	private void mergeRight()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = width - 1; c >= 0; c--)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r][c - 1])
						{
							currentState[r][c]++;
							currentState[r][c - 1] = 0;

							score += currentState[r][c];
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	private void mergeUp()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r + 1][c])
						{
							currentState[r][c]++;
							currentState[r + 1][c] = 0;

							score += currentState[r][c];
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	private void mergeDown()
	{
		for(int r = height - 1; r >= 0; r--)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r - 1][c])
						{
							currentState[r][c]++;
							currentState[r - 1][c] = 0;

							score += currentState[r][c];
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	private boolean isFilled(int r, int c)
	{
		return currentState[r][c] != 0;
	}

	private void generateNewSquare()
	{
		int h, w;
		boolean cont = true;

		while(cont)
		{
			randGen.setSeed(System.nanoTime());

			h = randGen.nextInt(height);
			w = randGen.nextInt(width);

			if(!isFilled(h, w))
			{
				if(randGen.nextInt(PROBABILITY) == 0)
				{
					currentState[h][w] = 2;
				}
				else
				{
					currentState[h][w] = 1;
				}

				cont = false;
			}
		}
	}

	private boolean boardIsFilled()
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(!isFilled(i, j))
				{
					return false;
				}
			}
		}

		return true;
	}

	private boolean leftIsPossible()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = c; i >= 0; i--)
					{
						if(!isFilled(r, i))
						{
							return true;
						}
					}
				}
			}
		}

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r][c + 1])
						{
							return true;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}

		return false;
	}

	private boolean rightIsPossible()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = width - 1; c >= 0; c--)
			{
				if(isFilled(r, c))
				{
					for(int i = c; i < width; i++)
					{
						if(!isFilled(r, i))
						{
							return true;
						}
					}
				}
			}
		}

		for(int r = 0; r < height; r++)
		{
			for(int c = width - 1; c >= 0; c--)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r][c - 1])
						{
							return true;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
					}
				}
			}
		}

		return false;
	}

	private boolean upIsPossible()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = r; i >= 0; i--)
					{
						if(!isFilled(i, c))
						{
							return true;
						}
					}
				}
			}
		}

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r + 1][c])
						{
							return true;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
					}
				}
			}
		}

		return false;
	}

	private boolean downIsPossible()
	{
		for(int r = height - 1; r >= 0; r--)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					for(int i = r; i < height; i++)
					{
						if(!isFilled(i, c))
						{
							return true;
						}
					}
				}
			}
		}

		for(int r = height - 1; r >= 0; r--)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					try
					{
						if(currentState[r][c] == currentState[r - 1][c])
						{
							return true;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
					}
				}
			}
		}

		return false;
	}

	public boolean gameOver()
	{
		if(!boardIsFilled())
		{
			return false;
		}

		if(leftIsPossible() || rightIsPossible() || upIsPossible() || downIsPossible())
		{
			return false;
		}

		return true;
	}

	public int[][] getCurrentState()
	{
		return currentState;
	}

	public int getScore()
	{
		return score;
	}
	
	public int getNumMoves()
	{
		return numMoves;
	}

	public void performRandomMove()
	{
		randGen.setSeed(System.nanoTime());
		
		int choice = randGen.nextInt(3);
		
		switch(choice)
		{
			case 0:
				shift(Board.LEFT);
				break;
				
			case 1:
				shift(Board.RIGHT);
				break;
				
			case 2:
				shift(Board.UP);
				break;
				
			case 3:
				shift(Board.DOWN);
				break;
		}
	}
}
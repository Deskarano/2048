import java.util.Random;

public class Board
{
	private int[][] currentState;
	private int height;
	private int width;
	private int exponent;
	private int score;
	private int numMoves;

	private static Random randGen = new Random();

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int[] MOVES = { LEFT, RIGHT, UP, DOWN };

	public static final int PROBABILITY = 100000000;

	public Board(int h, int w, int exp, int startScore)
	{
		height = h;
		width = w;
		exponent = exp;
		score = startScore;

		numMoves = 0;

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

	public Board(int[][] startState, int exp, int startScore)
	{
		height = startState.length;
		width = startState[0].length;
		exponent = exp;
		score = startScore;

		numMoves = 0;

		currentState = new int[height][width];

		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				currentState[i][j] = startState[i][j];
			}
		}
	}

	public void move(int direction)
	{
		numMoves++;

		shift(direction);
		merge(direction);
		shift(direction);

		generateNewSquare();
	}

	private void shift(int direction)
	{
		if(direction == LEFT)
		{
			shiftLeft();
		}

		else if(direction == RIGHT)
		{
			shiftRight();
		}

		else if(direction == UP)
		{
			shiftUp();
		}

		else if(direction == DOWN)
		{
			shiftDown();
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

	private void merge(int direction)
	{
		if(direction == LEFT)
		{
			mergeLeft();
		}
	
		else if(direction == RIGHT)
		{
			mergeRight();
		}
	
		else if(direction == UP)
		{
			mergeUp();
		}
	
		else if(direction == DOWN)
		{
			mergeDown();
		}
	
		else
		{
			// do nothing
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

							score += Math.pow(exponent, currentState[r][c]);
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

							score += Math.pow(exponent, currentState[r][c]);
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

							score += Math.pow(exponent, currentState[r][c]);
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

							score += Math.pow(exponent, currentState[r][c]);
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	public boolean moveIsPossible(int move)
	{
		if(move == LEFT)
		{
			return leftIsPossible();
		}

		else if(move == RIGHT)
		{
			return rightIsPossible();
		}

		else if(move == UP)
		{
			return upIsPossible();
		}

		else if(move == DOWN)
		{
			return downIsPossible();
		}

		else
		{
			return false;
		}
	}

	private boolean leftIsPossible()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(isFilled(r, c))
				{
					// check if merge is possible
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

					// check if move is possible
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

		return false;
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

	public boolean gameOver()
	{
		if(!boardIsFilled())
		{
			return false;
		}

		for(int i = 0; i < MOVES.length; i++)
		{
			if(moveIsPossible(MOVES[i]))
			{
				return false;
			}
		}

		return true;
	}

	private boolean isFilled(int r, int c)
	{
		return currentState[r][c] != 0;
	}

	private boolean boardIsFilled()
	{
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(!isFilled(r, c))
				{
					return false;
				}
			}
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

	public int getExponent()
	{
		return exponent;
	}

	public int getMaxNum()
	{
		int max = 0;

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(currentState[r][c] > max)
				{
					max = currentState[r][c];
				}
			}
		}

		return max;
	}

	public void performRandomMove()
	{
		randGen.setSeed(System.nanoTime());

		int nextMove = MOVES[randGen.nextInt(MOVES.length)];

		if(moveIsPossible(nextMove))
		{
			move(nextMove);
		}
		else
		{
			performRandomMove();
		}
	}

	public void performOptimalMove(int searchDepth)
	{
		// set up the table of moves
		int numOfPossibilities = (int) Math.pow(4, searchDepth);

		int[][] moves = new int[numOfPossibilities][searchDepth];
		int[] scores = new int[numOfPossibilities];

		int xPointer = 0;
		int yPointer = 0;

		while(xPointer < searchDepth)
		{
			// cycle
			for(int i = 0; i < Math.pow(4, xPointer); i++)
			{
				// left
				for(int j = 0; j < numOfPossibilities / Math.pow(4, xPointer + 1); j++)
				{
					moves[yPointer][xPointer] = LEFT;
					yPointer++;
				}

				// right
				for(int j = 0; j < numOfPossibilities / Math.pow(4, xPointer + 1); j++)
				{
					moves[yPointer][xPointer] = RIGHT;
					yPointer++;
				}

				// up
				for(int j = 0; j < numOfPossibilities / Math.pow(4, xPointer + 1); j++)
				{
					moves[yPointer][xPointer] = UP;
					yPointer++;
				}

				// down
				for(int j = 0; j < numOfPossibilities / Math.pow(4, xPointer + 1); j++)
				{
					moves[yPointer][xPointer] = DOWN;
					yPointer++;
				}
			}

			yPointer = 0;
			xPointer++;
		}

		for(int i = 0; i < numOfPossibilities; i++)
		{
			scores[i] = findFutureScore(moves[i]);
		}

		int indexOfMax = 0;
		int numBranches = 0;

		for(int i = 0; i < scores.length; i++)
		{
			if(scores[i] > -1)
			{
				numBranches++;
			}

			if(scores[i] > scores[indexOfMax])
			{
				indexOfMax = i;
			}
		}

		/*
		double percentage = (double) numBranches / numOfPossibilities * 100;
		
		System.out.println("Considering " + (int) percentage + "% of branches:");
		System.out.println("\tBest move: " + moves[indexOfMax][0]);
		System.out.println("\tCurrent score: " + score);
		System.out.println("\tPotential score: " + scores[indexOfMax]);
		System.out.println("\tDiff: " + (scores[indexOfMax] - score));
		System.out.println();
		*/

		if(numBranches == 0)
		{
			if(moveIsPossible(LEFT))
			{
				move(LEFT);
			}

			else if(moveIsPossible(RIGHT))
			{
				move(RIGHT);
			}

			else if(moveIsPossible(UP))
			{
				move(UP);
			}

			else if(moveIsPossible(DOWN))
			{
				move(DOWN);
			}
		}
		else
		{
			// possibility checking for this is handled in findFutureScore()
			move(moves[indexOfMax][0]);
		}

	}

	private int findFutureScore(int[] commands)
	{
		Board searchBoard = new Board(currentState, exponent, score);

		for(int i = 0; i < commands.length; i++)
		{
			if(searchBoard.gameOver())
			{
				return -1;
			}

			else if(searchBoard.moveIsPossible(commands[i]))
			{
				searchBoard.move(commands[i]);
			}
			else
			{
				return -1;
			}
		}

		return searchBoard.getScore();
	}
}

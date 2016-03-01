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

    public static final int PROBABILITY = 10;

    public Board(int h, int w, int exp)
    {
        height = h;
        width = w;
        exponent = exp;

        score = 0;
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

    public Board(int[][] startState, int exp)
    {
        height = startState.length;
        width = startState[0].length;
        exponent = exp;

        score = 0;
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

    public void shift(int command)
    {
        if(command == LEFT & leftIsPossible())
        {
            numMoves++;

            shiftLeft();
            mergeLeft();
            shiftLeft();

            generateNewSquare();
        }

        else if(command == RIGHT & rightIsPossible())
        {
            numMoves++;

            shiftRight();
            mergeRight();
            shiftRight();

            generateNewSquare();
        }

        else if(command == UP & upIsPossible())
        {
            numMoves++;

            shiftUp();
            mergeUp();
            shiftUp();

            generateNewSquare();
        }

        else if(command == DOWN & downIsPossible())
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

    public void shiftSeries(int[] commands)
    {
        for(int i = 0; i < commands.length; i++)
        {
            shift(commands[i]);
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
        if(!boardIsFilled() | leftIsPossible() | rightIsPossible() | upIsPossible() | downIsPossible())
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
        
        switch(randGen.nextInt(4))
        {
            case 0:
            	if(leftIsPossible())
            	{
            		shift(Board.LEFT);
            	}
            	else
            	{
            		performRandomMove();
            	}
                break;

            case 1:
            	if(rightIsPossible())
            	{
            		shift(Board.RIGHT);
            	}
            	else
            	{
            		performRandomMove();
            	}
                break;

            case 2:
            	if(upIsPossible())
            	{
            		shift(Board.UP);
            	}
            	else
            	{
            		performRandomMove();
            	}
                break;

            case 3:
            	if(downIsPossible())
            	{
            		shift(Board.DOWN);
            	}
            	else
            	{
            		performRandomMove();
            	}
                break;
        }
    }

    public void performOptimalMove(int searchDepth)
    {
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

        for(int i = 0; i < scores.length; i++)
        {
            if(scores[i] > scores[indexOfMax])
            {
                indexOfMax = i;
            }
        }

        shift(moves[indexOfMax][0]);
    }

    private int findFutureScore(int[] commands)
    {
        Board searchBoard = new Board(currentState, exponent);

        for(int i = 0; i < commands.length; i++)
        {
            searchBoard.shift(commands[i]);

            /*
             * if(searchBoard.gameOver()) { return -1; }
             */
        }

        return searchBoard.getScore();
    }
}
    private int exponent;
    private int score;
    private int numMoves;

    private static Random randGen = new Random();

    public static final String LEFT = "l";
    public static final String RIGHT = "r";
    public static final String UP = "u";
    public static final String DOWN = "d";

    public static final int PROBABILITY = 10;

    public Board(int h, int w, int exp)
    {
        height = h;
        width = w;
        exponent = exp;

        score = 0;
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

    public Board(int[][] startState, int exp)
    {
        height = startState.length;
        width = startState[0].length;
        exponent = exp;

        score = 0;
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

    public void shiftSeries(String[] commands)
    {
        for(int i = 0; i < commands.length; i++)
        {
            shift(commands[i]);
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

        if(leftIsPossible() | rightIsPossible() | upIsPossible() | downIsPossible())
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

    public int getExponent()
    {
        return exponent;
    }

    public void performRandomMove()
    {
        randGen.setSeed(System.nanoTime());

        int choice = randGen.nextInt(4);

        switch(choice)
        {
            case 0:
                shift(LEFT);
                break;

            case 1:
                shift(RIGHT);
                break;

            case 2:
                shift(UP);
                break;

            case 3:
                shift(DOWN);
                break;
        }
    }

    public void performOptimalMove(int searchDepth)
    {
        int numOfPossibilities = (int) Math.pow(4, searchDepth);

        String[][] moves = new String[numOfPossibilities][searchDepth];
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

        for(int i = 0; i < scores.length; i++)
        {
            if(scores[i] > scores[indexOfMax])
            {
                indexOfMax = i;
            }
        }

        shift(moves[indexOfMax][0]);
    }

    private int findFutureScore(int[] commands)
    {
        Board searchBoard = new Board(currentState, exponent);

        searchBoard.shiftSeries(commands);
        
        /*
        if(searchBoard.gameOver())
        {
            return -1;
        }
        */
        
        return searchBoard.getScore();
    }
}

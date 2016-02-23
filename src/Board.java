import java.util.Random;

public class Board
{
    private Tile[][] currentState;

    private int height;
    private int width;

    private static Random randGen = new Random();

    public Board(int h, int w)
    {
        height = h;
        width = w;

        currentState = new Tile[height][width];

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                currentState[i][j] = new Tile();
            }
        }

        generateNewSquare();
        generateNewSquare();
    }

    public void shift(String command)
    {
        if(command.equals("left"))
        {
            shiftLeft();
        }

        if(command.equals("right"))
        {
            shiftRight();
        }

        if(command.equals("up"))
        {
            shiftUp();
        }

        if(command.equals("down"))
        {
            shiftDown();
        }
    }

    private void shiftLeft()
    {
        int shiftPos = -1;

        // loop 1: merge if possible
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width - 1; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i][j + 1].getSquare())
                    {
                        currentState[i][j].incrementSquare();
                        currentState[i][j + 1].deleteSquare();
                    }
                }
            }
        }

        // loop 2: shift everything left
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    for(int k = j; k >= 0; k--)
                    {
                        if(!currentState[i][k].isFilled())
                        {
                            shiftPos = k;
                        }
                    }

                    if(shiftPos != -1)
                    {
                        currentState[i][shiftPos].setSquare(currentState[i][j].getSquare());
                        currentState[i][j].deleteSquare();

                        try
                        {
                            if(currentState[i][shiftPos - 1].getSquare() == currentState[i][shiftPos].getSquare())
                            {
                                currentState[i][shiftPos - 1].incrementSquare();
                                currentState[i][shiftPos].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                            // do nothing
                        }
                    }
                }
            }
        }

        generateNewSquare();
    }

    private void shiftRight()
    {
        int shiftPos = -1;

        // loop 1: merge if possible
        for(int i = 0; i < height; i++)
        {
            for(int j = width - 1; j > 0; j--)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i][j - 1].getSquare())
                    {
                        currentState[i][j].incrementSquare();
                        currentState[i][j - 1].deleteSquare();
                    }
                }
            }
        }

        // loop 2: shift everything right
        for(int i = 0; i < height; i++)
        {
            for(int j = width - 1; j >= 0; j--)
            {
                if(currentState[i][j].isFilled())
                {
                    for(int k = j; k < width; k++)
                    {
                        if(!currentState[i][k].isFilled())
                        {
                            shiftPos = k;
                        }
                    }

                    if(shiftPos != -1)
                    {
                        currentState[i][shiftPos].setSquare(currentState[i][j].getSquare());
                        currentState[i][j].deleteSquare();

                        try
                        {
                            if(currentState[i][shiftPos + 1].getSquare() == currentState[i][shiftPos].getSquare())
                            {
                                currentState[i][shiftPos + 1].incrementSquare();
                                currentState[i][shiftPos].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                            // do nothing
                        }
                    }
                }
            }
        }

        generateNewSquare();
    }

    private void shiftUp()
    {

    }

    private void shiftDown()
    {

    }

    // TODO: include searching for empty spaces
    // TODO: are these methods even necessary?

    private boolean shiftLeftPossible()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width - 1; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i][j + 1].getSquare())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean shiftRightPossible()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = width - 1; j > 0; j--)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i][j - 1].getSquare())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean shiftUpPossible()
    {
        for(int i = 0; i < height - 1; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i + 1][j].getSquare())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean shiftDownPossible()
    {
        for(int i = height - 1; i > 0; i--)
        {
            for(int j = 0; j < width; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getSquare() == currentState[i - 1][j].getSquare())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean generateNewSquare()
    {
        int h, w;

        while(true)
        {
            randGen.setSeed(System.nanoTime());

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

        if(shiftLeftPossible() | shiftRightPossible() | shiftUpPossible() | shiftDownPossible())
        {

            return false;
        }

        System.out.println("Game over");
        return true;
    }

    public String toString()
    {
        String returnString = "";

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                returnString += currentState[i][j].getSquare() + " ";
            }

            returnString += "\n";
        }

        return returnString;
    }
}
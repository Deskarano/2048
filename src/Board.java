import java.util.Random;

public class Board
{
    private Tile[][] currentState;
    private int height;
    private int width;
    private static Random randGen = new Random();
    
    public static final String LEFTCOMMAND = "l";
    public static final String RIGHTCOMMAND = "r";
    public static final String UPCOMMAND = "u";
    public static final String DOWNCOMMAND = "d";

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
    
    public Board(int h, int w, int[][] startState)
    {
        height = h;
        width = w;
        
        currentState = new Tile[height][width];
        
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                currentState[i][j] = new Tile();
                currentState[i][j].setSquare(startState[i][j]);
            }
        }
    }

    public void shift(String command)
    {
        if(command.equals(LEFTCOMMAND))
        {
            shiftLeft();
        }

        if(command.equals(RIGHTCOMMAND))
        {
            shiftRight();
        }

        if(command.equals(UPCOMMAND))
        {
            shiftUp();
        }

        if(command.equals(DOWNCOMMAND))
        {
            shiftDown();
        }
    }

    //TODO: find some way to simplify all these shift commands because holy shit
    
    private void shiftLeft()
    {
        int shiftPos = -1;

        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    try
                    {
                        if(currentState[r][c].getSquare() == currentState[r][c + 1].getSquare())
                        {
                            currentState[r][c].incrementSquare();
                            currentState[r][c + 1].deleteSquare();
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
            }
        }

        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    for(int i = c; i >= 0; i--)
                    {
                        if(!currentState[r][i].isFilled())
                        {
                            shiftPos = i;
                        }
                    }

                    if(shiftPos != -1)
                    {
                        currentState[r][shiftPos].setSquare(currentState[r][c].getSquare());
                        currentState[r][c].deleteSquare();

                        try
                        {
                            if(currentState[r][shiftPos - 1].getSquare() == currentState[r][shiftPos].getSquare())
                            {
                                currentState[r][shiftPos - 1].incrementSquare();
                                currentState[r][shiftPos].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {

                        }
                    }
                }

                shiftPos = -1;
            }
        }

        generateNewSquare();
    }

    private void shiftRight()
    {
        int shiftPos = -1;

        for(int r = 0; r < height; r++)
        {
            for(int c = width - 1; c >= 0; c--)
            {
                if(currentState[r][c].isFilled())
                {
                    try
                    {
                        if(currentState[r][c].getSquare() == currentState[r][c - 1].getSquare())
                        {
                            currentState[r][c].incrementSquare();
                            currentState[r][c - 1].deleteSquare();
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
            }
        }

        for(int r = 0; r < height; r++)
        {
            for(int c = width - 1; c >= 0; c--)
            {
                if(currentState[r][c].isFilled())
                {
                    for(int i = c; i < width; i++)
                    {
                        if(!currentState[r][i].isFilled())
                        {
                            shiftPos = i;
                        }
                    }

                    if(shiftPos != -1)
                    {
                        currentState[r][shiftPos].setSquare(currentState[r][c].getSquare());
                        currentState[r][c].deleteSquare();

                        try
                        {
                            if(currentState[r][shiftPos + 1].getSquare() == currentState[r][shiftPos].getSquare())
                            {
                                currentState[r][shiftPos + 1].incrementSquare();
                                currentState[r][shiftPos].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                            
                        }
                    }
                }

                shiftPos = -1;
            }
        }

        generateNewSquare();
    }

    private void shiftUp()
    {
        int shiftPos = -1;

        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    try
                    {
                        if(currentState[r][c].getSquare() == currentState[r + 1][c].getSquare())
                        {
                            currentState[r][c].incrementSquare();
                            currentState[r + 1][c].deleteSquare();
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {
                        
                    }
                }
            }
        }

        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    for(int i = r; i >= 0; i--)
                    {
                        if(!currentState[i][c].isFilled())
                        {
                            shiftPos = i;
                        }
                    }

                    if(shiftPos != -1)
                    {
                        currentState[shiftPos][c].setSquare(currentState[r][c].getSquare());
                        currentState[r][c].deleteSquare();

                        try
                        {
                            if(currentState[shiftPos - 1][c].getSquare() == currentState[shiftPos][c].getSquare())
                            {
                                currentState[shiftPos - 1][c].incrementSquare();
                                currentState[shiftPos][c].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {

                        }
                    }
                }

                shiftPos = -1;
            }
        }

        generateNewSquare();
    }

    private void shiftDown()
    {
        int shiftPos = -1;

        for(int r = height - 1; r >= 0; r--)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    try
                    {
                        if(currentState[r][c].getSquare() == currentState[r - 1][c].getSquare())
                        {
                            currentState[r][c].incrementSquare();
                            currentState[r - 1][c].deleteSquare();
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
            }
        }
        
        for(int r = height - 1; r >= 0; r--)
        {
            for(int c = 0; c < width; c++)
            {
                if(currentState[r][c].isFilled())
                {
                    for(int i = r; i < height; i++)
                    {
                        if(!currentState[i][c].isFilled())
                        {
                            shiftPos = i;
                        }
                    }
                    
                    if(shiftPos != -1)
                    {
                        currentState[shiftPos][c].setSquare(currentState[r][c].getSquare());
                        currentState[r][c].deleteSquare();
                        
                        try
                        {
                            if(currentState[shiftPos + 1][c].getSquare() == currentState[shiftPos][c].getSquare())
                            {
                                currentState[shiftPos + 1][c].incrementSquare();
                                currentState[shiftPos][c].deleteSquare();
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                            
                        }
                    }
                }
                
                shiftPos = -1;
            }
        }
        
        generateNewSquare();
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

    //TODO: actually finish this
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
                if(currentState[i][j].getSquare() == 0)
                {
                    returnString += "- ";
                }
                else
                {
                    returnString += currentState[i][j].getSquare() + " ";
                }
            }

            returnString += "\n";
        }

        return returnString;
    }
    
    public int[][] getCurrentState()
    {
        int[][] returnArray = new int[height][width];
        
        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                returnArray[r][c] = currentState[r][c].getSquare();
            }
        }

        return returnArray;
    }
}
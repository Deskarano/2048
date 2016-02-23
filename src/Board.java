import java.util.Random;

public class Board
{
    private Tile[][] currentState;

    private int height;
    private int width;

    private static Random randGen = new Random();

    public Board(int h, int w)
    {
        System.out.println("initialized board");
        
        height = h;
        width = w;

        currentState = new Tile[height][width];

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
        if(shiftLeftPossible())
        {
            for(int i = 0; i < height; i++)
            {
                for(int j = 0; j < width - 1; j++)
                {
                    if(currentState[i][j].isFilled())
                    {
                        if(currentState[i][j].getValue() == currentState[i][j + 1].getValue())
                        {
                            currentState[i][j].incrementSquare();
                            currentState[i][j + 1].deleteSquare();
                        }
                    }
                }
            }
            
            generateNewSquare();
        }
        
    }

    private void shiftRight()
    {
        if(shiftRightPossible())
        {
            for(int i = 0; i < height; i++)
            {
                for(int j = width - 1; j > 0; j--)
                {
                    if(currentState[i][j].isFilled())
                    {
                        if(currentState[i][j].getValue() == currentState[i][j - 1].getValue())
                        {
                            currentState[i][j].incrementSquare();
                            currentState[i][j - 1].deleteSquare();
                        }
                    }
                }
            }
            
            generateNewSquare();
        }     
    }

    private void shiftUp()
    {
        if(shiftUpPossible())
        {
            for(int i = 0; i < height - 1; i++)
            {
                for(int j = 0; j < width; j++)
                {
                    if(currentState[i][j].isFilled())
                    {
                        if(currentState[i][j].getValue() == currentState[i + 1][j].getValue())
                        {
                            currentState[i][j].incrementSquare();
                            currentState[i + 1][j].deleteSquare();
                        }
                    }
                }
            }
            
            generateNewSquare();
        }
    }

    private void shiftDown()
    {
        if(shiftDownPossible())
        {
            for(int i = height - 1; i > 0; i--)
            {
                for(int j = 0; j < width; j++)
                {
                    if(currentState[i][j].isFilled())
                    {
                        if(currentState[i][j].getValue() == currentState[i - 1][j].getValue())
                        {
                            currentState[i][j].incrementSquare();
                            currentState[i - 1][j].deleteSquare();
                        }
                    }
                }
            }
            
            generateNewSquare();
        }
    }

    private boolean shiftLeftPossible()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width - 1; j++)
            {
                if(currentState[i][j].isFilled())
                {
                    if(currentState[i][j].getValue() == currentState[i][j + 1].getValue())
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
                    if(currentState[i][j].getValue() == currentState[i][j - 1].getValue())
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
                    if(currentState[i][j].getValue() == currentState[i + 1][j].getValue())
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
                    if(currentState[i][j].getValue() == currentState[i - 1][j].getValue())
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

    private boolean gameOver()
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
            return true;
        }
        
        return false;
    }
    
    public String toString()
    {
        String returnString = "";
        
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                returnString += currentState[i][j].getValue() + " ";
            }
            
            returnString += "\n";
        }
        
        return returnString;
    }
}
import java.io.*;

public class RunSimulation
{
    private static int BOARDHEIGHT = 4;
    private static int BOARDWIDTH = 4;
    private static int SEARCHDEPTH = 4;

    private static Board mainBoard;
    private static PrintWriter fileWriter;
    private static String name;

    public static void main(String[] args)
    {
        name = BOARDHEIGHT + "x" + BOARDWIDTH + "-" + SEARCHDEPTH;

        try
        {
            fileWriter = new PrintWriter(name + ".txt", "UTF-8");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        int trial = 0;

        while(trial < 100)
        {
            trial++;
            mainBoard = new Board(BOARDHEIGHT, BOARDWIDTH, 2);

            while(!mainBoard.gameOver())
            {
                mainBoard.performOptimalMove(SEARCHDEPTH);
            }

            String dataString = trial + "\t" + SEARCHDEPTH + '\t' + mainBoard.getScore() + '\t'
                    + mainBoard.getNumMoves() + '\t' + (int) Math.pow(2, mainBoard.getMaxNum()) + '\n';
            
            System.out.println(dataString);
            fileWriter.println(dataString);
            fileWriter.flush();
        }

        fileWriter.close();
    }
}

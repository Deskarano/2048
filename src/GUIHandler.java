import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GUIHandler
{   
    private static JFrame component_mainFrame;

    private static JTextArea component_mainTextArea;
    private static JTextArea component_outputTextArea;

    private static JScrollPane component_outputScrollPane;
    private static String outputString;

    private static final Font mainFont = new Font("Main", Font.PLAIN, 16);

    private static final int BOARDHEIGHT = 4;
    private static final int BOARDWIDTH = 4;
    private static final int SEARCHDEPTH = 4;

    private static Board mainBoard;
    private static PrintWriter fileWriter;
    private static String name;

    public static void main(String[] args)
    {
        outputString = "";
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
        
        instantiate_frame();
        instantiate_components();
        instantiate_finalize();      

        int trial = 0;

        while(trial < 100)
        {
            trial++;
            mainBoard = new Board(BOARDHEIGHT, BOARDWIDTH, 2);

            while(!mainBoard.gameOver())
            {
                mainBoard.performOptimalMove(SEARCHDEPTH);
                update_display();
            }

            String dataString = trial + "\t" + SEARCHDEPTH + '\t' + mainBoard.getScore() + '\t'
                    + mainBoard.getNumMoves() + '\t' + (int) Math.pow(2, mainBoard.getMaxNum()) + '\n';

            outputString += dataString;
            update_output();
            
            System.out.println(dataString);
            fileWriter.println(dataString);
            fileWriter.flush();
        }

        fileWriter.close();
    }

    
    private static void instantiate_frame()
    {
        component_mainFrame = new JFrame("2048: " + name);
        component_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        component_mainFrame.setLayout(new FlowLayout());
        component_mainFrame.setResizable(true);
    }

    private static void instantiate_components()
    {
        component_mainTextArea = new JTextArea(BOARDHEIGHT, BOARDWIDTH);
        component_mainTextArea.setEditable(false);
        component_mainTextArea.setFont(mainFont);

        component_outputTextArea = new JTextArea(25, 35);
        component_outputTextArea.setEditable(false);
        component_outputTextArea.setFont(mainFont);

        component_outputScrollPane = new JScrollPane(component_outputTextArea);

        component_mainTextArea.addKeyListener(new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent arg0)
            {
                if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    mainBoard.shift(Board.LEFT);
                    update_display();
                }

                if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    mainBoard.shift(Board.RIGHT);
                    update_display();
                }

                if(arg0.getKeyCode() == KeyEvent.VK_UP)
                {
                    mainBoard.shift(Board.UP);
                    update_display();
                }

                if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    mainBoard.shift(Board.DOWN);
                    update_display();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }

            @Override
            public void keyTyped(KeyEvent e)
            {

            }
        });
    }

    private static void instantiate_finalize()
    {
        component_mainFrame.add(component_mainTextArea);
        component_mainFrame.add(component_outputScrollPane);

        component_mainFrame.setMinimumSize(new Dimension(125 * BOARDWIDTH, 35 * BOARDHEIGHT + 250));
        component_mainFrame.pack();
        component_mainFrame.setVisible(true);
    }

    private static void update_display()
    {
        int[][] data = mainBoard.getCurrentState();
        String printString = "";

        for(int r = 0; r < BOARDHEIGHT; r++)
        {
            for(int c = 0; c < BOARDWIDTH; c++)
            {
                if(data[r][c] == 0)
                {
                    printString += ".\t";
                }
                else
                {
                    printString += (int) Math.pow(mainBoard.getExponent(), data[r][c]) + "\t";
                }
            }

            printString += "\n";
        }

        component_mainTextArea.setText(printString);
    }

    private static void update_output()
    {
        component_outputTextArea.setText(outputString);
    }
}

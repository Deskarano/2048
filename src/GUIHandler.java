import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIHandler
{
    private static JFrame component_mainFrame;
    private static JTextArea component_mainTextArea;

    private static final Font mainFont = new Font("Main", Font.PLAIN, 16);

    private static final int BOARDHEIGHT = 8;
    private static final int BOARDWIDTH = 8;

    private static Board mainBoard;

    public static void main(String[] args)
    {
        instantiate_frame();
        instantiate_components();
        instantiate_finalize();

        mainBoard = new Board(BOARDHEIGHT, BOARDWIDTH, 2);
        update_text("");

        while(!mainBoard.gameOver())
        {
            mainBoard.performOptimalMove(4);
            update_text("");
        }

        System.out.println("Score: " + mainBoard.getScore());
        System.out.println("Moves: " + mainBoard.getNumMoves());
    }

    private static void instantiate_frame()
    {
        component_mainFrame = new JFrame("2048");
        component_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        component_mainFrame.setLayout(new FlowLayout());
        component_mainFrame.setResizable(true);
    }

    private static void instantiate_components()
    {
        component_mainTextArea = new JTextArea(BOARDHEIGHT, BOARDWIDTH);
        component_mainTextArea.setEditable(false);
        component_mainTextArea.setFont(mainFont);

        component_mainTextArea.addKeyListener(new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent arg0)
            {
                if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    mainBoard.shift(Board.LEFT);
                    update_text("");
                }

                if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    mainBoard.shift(Board.RIGHT);
                    update_text("");
                }

                if(arg0.getKeyCode() == KeyEvent.VK_UP)
                {
                    mainBoard.shift(Board.UP);
                    update_text("");
                }

                if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    mainBoard.shift(Board.DOWN);
                    update_text("");
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

        component_mainFrame.setMinimumSize(new Dimension(125 * BOARDWIDTH, 35 * BOARDHEIGHT));
        component_mainFrame.pack();
        component_mainFrame.setVisible(true);
    }

    private static void update_text(String optionalText)
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

        printString += optionalText;

        component_mainTextArea.setText(printString);
    }
}

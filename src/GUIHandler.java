import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIHandler
{
    private static JFrame component_mainFrame;

    private static JTextArea component_mainTextArea;
    
    private static JScrollPane component_mainScrollPane;

    private static GridBagConstraints constraints_mainTextArea;

    private static final int BOARDHEIGHT = 4;
    private static final int BOARDWIDTH = 4;

    private static Board mainBoard;

    public static void main(String[] args)
    {
        instantiate_frame();
        instantiate_components();
        instantiate_constraints();
        instantiate_finalize();

        mainBoard = new Board(4, 4);
        update_text("");
    }

    private static void instantiate_frame()
    {
        component_mainFrame = new JFrame("2048");
        component_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        component_mainFrame.setLayout(new GridBagLayout());
        component_mainFrame.setResizable(false);
    }

    private static void instantiate_components()
    {
        component_mainTextArea = new JTextArea();
        component_mainTextArea.setEditable(false);
        
        component_mainScrollPane = new JScrollPane(component_mainTextArea);
        
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

    private static void instantiate_constraints()
    {
        constraints_mainTextArea = new GridBagConstraints();
        constraints_mainTextArea.gridx = 0;
        constraints_mainTextArea.gridy = 0;
        constraints_mainTextArea.gridwidth = 5;
        constraints_mainTextArea.gridheight = 5;
        constraints_mainTextArea.ipadx = 500;
        constraints_mainTextArea.ipady = 500;
        constraints_mainTextArea.insets = new Insets(5, 5, 5, 5);
        constraints_mainTextArea.fill = GridBagConstraints.BOTH;     
    }

    private static void instantiate_finalize()
    {
        component_mainFrame.add(component_mainScrollPane, constraints_mainTextArea);


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
                    printString += "-\t";
                }
                else
                {
                    printString += (int)Math.pow(2, data[r][c]) + "\t";
                }      
            }
            
            printString += "\n";
        }
        
        printString += optionalText;
        
        component_mainTextArea.setText(printString);
    }
}

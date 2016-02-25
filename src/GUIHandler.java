import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIHandler
{
    private static JFrame component_mainFrame;

    private static JTextArea component_mainTextArea;
    
    private static JScrollPane component_mainScrollPane;

    private static JButton component_left;
    private static JButton component_right;
    private static JButton component_up;
    private static JButton component_down;
    private static JButton component_auto;

    private static GridBagConstraints constraints_mainTextArea;
    private static GridBagConstraints constraints_left;
    private static GridBagConstraints constraints_right;
    private static GridBagConstraints constraints_up;
    private static GridBagConstraints constraints_down;
    private static GridBagConstraints constraints_auto;

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
        update_text();
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

        component_left = new JButton("Left");
        component_right = new JButton("Right");
        component_up = new JButton("Up");
        component_down = new JButton("Down");
        component_auto = new JButton("Auto");

        component_left.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainBoard.shift(Board.LEFT);
                update_text();
            }
        });

        component_right.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainBoard.shift(Board.RIGHT);
                update_text();
            }
        });
        
        component_up.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainBoard.shift(Board.UP);
                update_text();
            }
        });
        
        component_down.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainBoard.shift(Board.DOWN);
                update_text();
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

        constraints_left = new GridBagConstraints();
        constraints_left.gridx = 7;
        constraints_left.gridy = 3;
        constraints_left.gridwidth = 1;
        constraints_left.gridheight = 1;
        constraints_left.ipadx = 100;
        constraints_left.ipady = 100;
        constraints_left.insets = new Insets(5, 5, 5, 5);
        constraints_left.fill = GridBagConstraints.BOTH;

        constraints_right = new GridBagConstraints();
        constraints_right.gridx = 9;
        constraints_right.gridy = 3;
        constraints_right.gridwidth = 1;
        constraints_right.gridheight = 1;
        constraints_right.ipadx = 100;
        constraints_right.ipady = 100;
        constraints_right.insets = new Insets(5, 5, 5, 5);
        constraints_right.fill = GridBagConstraints.BOTH;

        constraints_up = new GridBagConstraints();
        constraints_up.gridx = 8;
        constraints_up.gridy = 2;
        constraints_up.gridwidth = 1;
        constraints_up.gridheight = 1;
        constraints_up.ipadx = 100;
        constraints_up.ipady = 100;
        constraints_up.insets = new Insets(5, 5, 5, 5);
        constraints_up.fill = GridBagConstraints.BOTH;

        constraints_down = new GridBagConstraints();
        constraints_down.gridx = 8;
        constraints_down.gridy = 4;
        constraints_down.gridwidth = 1;
        constraints_down.gridheight = 1;
        constraints_down.ipadx = 100;
        constraints_down.ipady = 100;
        constraints_down.insets = new Insets(5, 5, 5, 5);
        constraints_down.fill = GridBagConstraints.BOTH;
        
        constraints_auto = new GridBagConstraints();
        constraints_auto.gridx = 8;
        constraints_auto.gridy = 3;
        constraints_auto.gridwidth = 1;
        constraints_auto.gridheight = 1;
        constraints_auto.ipadx = 100;
        constraints_auto.ipady = 100;
        constraints_auto.insets = new Insets(5, 5, 5, 5);
        constraints_auto.fill = GridBagConstraints.BOTH;
    }

    private static void instantiate_finalize()
    {
        component_mainFrame.add(component_mainScrollPane, constraints_mainTextArea);
        component_mainFrame.add(component_left, constraints_left);
        component_mainFrame.add(component_right, constraints_right);
        component_mainFrame.add(component_up, constraints_up);
        component_mainFrame.add(component_down, constraints_down);
        component_mainFrame.add(component_auto, constraints_auto);

        component_mainFrame.pack();
        component_mainFrame.setVisible(true);
    }

    private static void update_text()
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
        
        component_mainTextArea.setText(printString);
    }
}

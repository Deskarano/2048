import java.util.Scanner;

public class GUIHandler
{
    public static void main(String[] args)
    {
        Board mainBoard = new Board(4, 4);
        Scanner input = new Scanner(System.in);

        while(!mainBoard.gameOver())
        {
            printBoard(mainBoard.getCurrentState(), 4, 4);

            System.out.println("Next move: ");
            mainBoard.shift(input.nextLine());
            System.out.println();
        }

        System.out.println(mainBoard);
        input.close();
    }

    public static void printBoard(int[][] board, int height, int width)
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(board[i][j] == 0)
                {
                    System.out.print("-\t");
                }
                else
                {
                    System.out.print((int) Math.pow(2, board[i][j]));
                    System.out.print("\t");
                }
            }

            System.out.println();
            ;
        }
    }
}
import java.util.Scanner;

public class GUIHandler
{
    public static void main(String[] args)
    {
        Board mainBoard = new Board(5, 5);
        Scanner input = new Scanner(System.in);

        while(!mainBoard.gameOver())
        {
            System.out.println(mainBoard);
            System.out.println("Next move: ");
            mainBoard.shift(input.nextLine());
            System.out.println();
        }

    }
}
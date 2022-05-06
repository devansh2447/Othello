
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Main
{
    public static void main(String args[]) throws Exception {
        Board board = new Board();
        board = Board.init(board);
        Board.printBoard(board);
        System.out.println("Welcome to the Java host for Othello gameplay.");
        System.out.println("To see the instructions, please press \"i\", and press any other key to continue (your browser will be opened if you choose to view the instructions).");
        Scanner scan = new Scanner(System.in);
        String check = scan.nextLine();
        if(check.equalsIgnoreCase("i")){
            System.out.println("Launching browser...");
            Instructions.launchInstructions();
        }
        Scanner checkIfLog = new Scanner(System.in);
        System.out.println("Press \"y\" is you want the game to be logged.");
        String log = checkIfLog.nextLine();
        Scanner playAgainstComputer = new Scanner(System.in);
        System.out.println("Press \"y\" is you want to play against the computer.");
        String computer = playAgainstComputer.nextLine();
        System.out.println("To provide your input, type the x and y coordinates (both values should be in the range 1-8).");
        System.out.println("Keep in mind that the top left corner is 1,1 (if you are unsure, just refer to the numbers at the borders of the board).");
        if(computer.equalsIgnoreCase("y")){
            ComputerInterface.init(log.equalsIgnoreCase("y"));
        }
        else{
            Interface.init(log.equalsIgnoreCase("y"));
        }
    }
    
    public static void continuePlay() throws java.io.IOException {
        Scanner checkIfLog = new Scanner(System.in);
        System.out.println("Press \"y\" is you want the game to be logged.");
        String log = checkIfLog.nextLine();
        Scanner playAgainstComputer = new Scanner(System.in);
        System.out.println("Press \"y\" is you want to play against the computer.");
        String computer = playAgainstComputer.nextLine();
        System.out.println("To provide your input, type the x and y coordinates (both values should be in the range 1-8).");
        System.out.println("Keep in mind that the top left corner is 1,1 (if you are unsure, just refer to the numbers at the borders of the board).");
        if(computer.equalsIgnoreCase("y")){
            ComputerInterface.init(log.equalsIgnoreCase("y"));
        }
        else{
            Interface.init(log.equalsIgnoreCase("y"));
        }
    }
}

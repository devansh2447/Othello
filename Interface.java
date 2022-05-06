//Gets input, prints output and determines the winner for user versus user gameplay. Contains methods to do the same.

import java.util.Scanner;
import java.io.FileWriter;
public class Interface
{
    public static Board getOutput(Board current, Piece toAdd) throws java.io.IOException {
        current = Board.addPiece(current, toAdd);
        if(current.state.equals("inv") || current.state.equals("ovr")){
            return current;
        }
        else{
            current = Flip.flip(toAdd, current);
            return current;
        }
    }

    public static void getInput(String colour, Board current, boolean isFirstTime, int filledSquares) throws java.io.IOException {
        current.state  = "";
        if(isFirstTime == false && LegalMove.isLegalMovePossible(current, colour) == false){
            if(colour.equals("b")){
                if(LegalMove.isLegalMovePossible(current, "w") == false){
                    System.out.println("Game over.");
                    if(current.log == true){
                        Logger.write(Logger.getPath(), "Game over: no moves possible.!");
                    }
                    current.log = false;
                    over(current);
                }
                else{
                    System.out.println("Black has no legal moves, so white will move");
                    if(current.log == true){
                        Logger.write(Logger.getPath(), "White to move (black has no legal moves)!");
                    }
                    getInput("w", current, isFirstTime, filledSquares);
                }
            }
            else{
                if(LegalMove.isLegalMovePossible(current, "b") == false){
                    System.out.println("Game over.");
                    if(current.log == true){
                        Logger.write(Logger.getPath(), "Game over: no moves possible.!");
                    }
                    current.log = false;
                    over(current);
                }
                else{
                    System.out.println("White has no legal moves, so black will move");
                    if(current.log == true){
                        Logger.write(Logger.getPath(), "Black to move (white has no legal moves)!");
                    }
                    getInput("b", current, isFirstTime, filledSquares);
                }
            }
        }
        else if(filledSquares < 64){
            Scanner getX = new Scanner(System.in);
            Scanner getY = new Scanner(System.in);
            if(colour.equals("b")){
                System.out.println("Black's move.");
            }
            else{
                System.out.println("White's move.");
            }
            System.out.println("Please provide your desired x coordinate");
            int x = getX.nextInt();
            System.out.println("Please provide your desired y coordinate");
            int y = getY.nextInt();
            if(current.log == true){
                Logger.write(Logger.getPath(), x + "" +  y + colour + "!");
            }
            if(x == 0 && y == 0){ //to stop execution of program
                if(current.log == true){
                    Logger.write(Logger.getPath(), "Manual exit by user.!");
                }
                exit(current);
                System.exit(0);
            }
            Piece toAdd = new Piece(x, y, colour);
            toAdd = Piece.updateControls(toAdd, current);
            boolean isEmpty = LegalMove.isEmpty(toAdd.controls);
            Piece[] old = clonePieceArray(current.pieces);
            Board newBoard = getOutput(current, toAdd);
            newBoard.log = current.log;
            if(newBoard.state.equals("") && isEmpty == false && filledSquares < 64){
                Board.printBoard(current);
                if(colour.equals("b")){
                    colour = "w";
                    getInput(colour, newBoard, false, filledSquares + 1);
                }
                else{
                    colour = "b";
                    getInput(colour, newBoard, false, filledSquares + 1);
                }
            }
            else if(newBoard.state.equals("inv") || isEmpty == true || LegalMove.isLegalMove(toAdd.x, toAdd.y, current, toAdd.colour) == false){
                System.out.println("Invalid move, please try again");
                if(current.log == true){
                    Logger.write(Logger.getPath(), "Invalid move.!");
                }
                current.pieces = old;
                getInput(colour, current, isFirstTime, filledSquares);
            }
        }
        else{
            over(current);
        }
    }

    public static void over(Board board) throws java.io.IOException {
        Board.printBoard(board);
        System.out.println("Game over.");
        if(board.log == true){
            Logger.write(Logger.getPath(), "Game over - board is full.!");
        }
        System.out.println("Hold on while we check who won...");
        winner(board);
        Scanner checkIfPlayAgain = new Scanner(System.in);
        String check;
        System.out.println("Press \"e\" to exit, and any other key to continue...");
        check = checkIfPlayAgain.nextLine();
        if(check.equalsIgnoreCase("e")){
            if(board.log == true){
                Logger.write(Logger.getPath(), "Exiting program...!");
            }
            exit(board);
            System.exit(0);
        }
        else if(check.equalsIgnoreCase("f")){
            if(board.log == true){
                Logger.write(Logger.getPath(), "Respects paid.!");
            }
            System.out.println("Respects paid.");
            System.out.println("Now, press \"e\" to exit, and any other key to continue...");
            String check2 = checkIfPlayAgain.nextLine();
            if(check2.equalsIgnoreCase("e")){
                if(board.log == true){
                    Logger.write(Logger.getPath(), "Exiting program...!");
                }
                exit(board);
                System.exit(0);
            }
            else{
                if(board.log == true){
                    Logger.write(Logger.getPath(), "New game started... !");
                }
                System.out.println();
                Main.continuePlay();
            }
        }
        else{
            if(board.log == true){
                Logger.write(Logger.getPath(), "New game started...!");
            }
            System.out.println();
            init(board.log);
        }
    }

    public static void winner(Board check) throws java.io.IOException {
        Piece[] reference;
        reference = check.pieces;
        int blackLeads = 0;
        for(int iter = 1; iter <= 64; iter++){
            if(reference[iter] != null){
                if(reference[iter].colour.equals("b")){
                    blackLeads = blackLeads + 1;
                }
                else{
                    blackLeads = blackLeads - 1;
                }
            }
        }
        if(blackLeads < 0){
            System.out.println("Looks like white has won by a margin of " + Math.abs(blackLeads) + " squares!");
            if(check.log == true){
                Logger.write(Logger.getPath(), "White wins by margin of " + Math.abs(blackLeads) + " squares.!");
            }
        }
        else if(blackLeads > 0){
            System.out.println("Looks like black has won by a margin of " + blackLeads + " squares!");
            if(check.log == true){
                Logger.write(Logger.getPath(), "Black wins by margin of " + blackLeads + " squares.!");
            }
        }
        else{
            System.out.println("Looks like it is a draw!");
            if(check.log == true){
                Logger.write(Logger.getPath(), "Game has ended in a draw.!");
            }
        }
    }

    public static Piece[] clonePieceArray(Piece[] toClone){
        Piece[] forReturn = new Piece[toClone.length];
        for(int iter = 0; iter < toClone.length; iter++){
            if(toClone[iter] != null){
                Piece assign = new Piece(toClone[iter].x, toClone[iter].y, toClone[iter].colour, toClone[iter].controls);
                forReturn[iter] = assign;
            }
        }
        return forReturn;
    }

    public static void exit(Board board) throws java.io.IOException, java.io.FileNotFoundException {
        if(board.log == true){
            System.out.println("Log file saved at " + Logger.getPath());
            String assign = Logger.processFile(Logger.getPath());
            Logger.createDirectory();
            String finalPath = Logger.getFinalPath();
            System.out.println("Backup saved at " + finalPath);
            Logger.createFile(finalPath);
            FileWriter write = new FileWriter(finalPath);    
            write.write(assign);
            write.close();
            Logger.replace(assign);
            String backup = Logger.processFile(finalPath);
            Logger.replaceBackup(backup);
        }
        System.out.flush();
    }

    public static void init(boolean log) throws java.io.IOException {
        Board board = new Board();
        board = Board.init(board);
        board.log = log;
        if(log == true){
            Logger.createFile(Logger.getPath());
            FileWriter write = new FileWriter(Logger.getPath());    
            write.write("Logging has begun...!");
            write.close();
        }
        getInput("b", board, true, 4);
    }
}

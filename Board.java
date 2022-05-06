
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    Piece[] pieces;
    String state;
    boolean log;

    public Board(){
        this.pieces = new Piece[65];
    }

    public static Board init(Board toModify){
        toModify = addPiece(4, 4, "b", toModify);
        toModify = addPiece(5, 4, "w", toModify);
        toModify = addPiece(4, 5, "w", toModify);
        toModify = addPiece(5, 5, "b", toModify);
        return toModify;
    }

    public static Piece getPiece(Board board, int x, int y){
        return board.pieces[Piece.getPosition(x, y)];
    }

    public static boolean isOpposite(int x, int y, Board board, String colour){
        Piece check = getPiece(board, x, y);
        return check != null && check.colour.equals(colour) == false;
    }

    public static boolean isSame(int x, int y, Board board, String colour){
        Piece check = getPiece(board, x, y);
        return check != null && check.colour.equals(colour) == true;
    }

    public static Board addPiece(int x, int y, String colour, Board toModify){
        return addPiece(toModify, new Piece(x, y, colour));
    }

    public static Board addPiece(Board toModify, Piece toAdd){
        int position = Piece.getPosition(toAdd.x, toAdd.y);
        Piece updated = Piece.updateControls(toAdd, toModify);
        toModify.state = "";
        if(toModify.pieces[position] == null){
            toModify.pieces[position] = toAdd;
        }
        else if(toModify.pieces[position] != null){
            toModify.state = "inv";
        }
        else if(LegalMove.isEmpty(updated.controls)){
            toModify.state = "inv";
        }
        else{
            toModify.state = "ovr";
        }
        return toModify;
    }

    public static void printBoard(Board toPrint){
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int iter = 1; iter <= 8; iter++){
            System.out.print(iter + " ");
            for(int column = 1; column <= 8; column++){
                int position = Piece.getPosition(column, iter);
                if(toPrint.pieces[position] != null){
                    System.out.print(toPrint.pieces[position].colour + " ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}


/**
 * Write a description of class Flip here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Flip
{
    public static int[] getArray(int x, int y, Board board, String colour){
        int[] forReturn = new int[28];
        forReturn = Controls.getFile(x, y, 1, 0, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, 1, 1, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, 0, 1, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, -1, 1, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, -1, 0, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, -1, -1, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, 0, -1, forReturn, board, colour);
        forReturn = Controls.getFile(x, y, 1, -1, forReturn, board, colour);
        return forReturn;
    }

    public static Board flip(int x, int y, Board board, String colour) throws java.io.IOException {
        int[] controls = getArray(x, y, board, colour);
        Piece[] toModify;
        toModify = board.pieces;
        int xModify;
        int yModify;
        int position;
        int iter = 0;
        while(controls[iter] != 0){
            xModify = (controls[iter] - controls[iter]%10)/10;
            yModify = controls[iter]%10;
            position = Piece.getPosition(xModify, yModify);
            toModify[position] = Piece.invert(toModify[position]);
            iter++;
        }
        if(board.log == true){
            String write = arrayAsString(controls);
            Logger.write(Logger.getPath(), "Flipped squares: " + write + "!");
        }
        board.pieces = toModify;
        return board;
    }

    public static String arrayAsString(int[] convert){
        String forReturn = "";
        for(int iter = 0; iter < convert.length; iter++){
            if(convert[iter] != 0){
                forReturn = forReturn + " " + convert[iter];
            }
        }
        return forReturn + "  ";
    }

    public static Board flip(Piece check, Board board) throws java.io.IOException {
        return flip(check.x, check.y, board, check.colour);
    }
}


/**
 * Write a description of class LegalMove here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LegalMove
{
    public static boolean isLegalMovePossible(Board reference, String colour){
        int xCheck;
        int yCheck;
        for(int iter = 1; iter < 65; iter++){
            if(reference.pieces[iter] == null){
                xCheck = Piece.getX(iter);
                yCheck = Piece.getY(iter);
                int[] controls = Flip.getArray(xCheck, yCheck, reference, colour);
                if(isEmpty(controls) == false){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isEmpty(int[] toCheck){
        for(int iter = 0; iter < toCheck.length; iter++){
            if(toCheck[iter] != 0){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotEmpty(int[] toCheck){
        if(isEmpty(toCheck) == true){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean isLegalMove(int x, int y, Board reference, String colour){
        Piece check = new Piece(x, y, colour);
        check = Piece.updateControls(check, reference);
        return isNotEmpty(check.controls);
    }
}

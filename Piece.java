
/**
 * Write a description of class Piece here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Piece
{
    int x;
    int y; //coordinates start from top left
    int[] controls;
    String colour;
    
    public Piece(int x, int y, String colour){
        this.x = x;
        this.y = y;
        this.colour = colour;
    }
    
    public Piece(int x, int y, String colour, int[] controls){
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.controls = controls;
    }
    
    public static Piece updateControls(Piece toUpdate, Board reference){
        toUpdate.controls = Flip.getArray(toUpdate.x, toUpdate.y, reference, toUpdate.colour);
        return toUpdate;
    }
    
    public static int getPosition(int x, int y){
        return (y - 1) * 8 + x;
    }
    
    public static int getY(int position){
        return (position - position%8)/8 + 1;
    }
    
    public static int getX(int position){
        return position%8;
    }
    
    public static Piece invert(Piece toInvert){
        if(toInvert.colour.equals("b")){
            toInvert.colour = "w";
        }
        else{
            toInvert.colour = "b";
        }
        return toInvert;
    }
}

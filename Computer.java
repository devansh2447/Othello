
/**
 * Write a description of class Computer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
public class Computer
{
    public static int getBestMove(Board check, String colour){ //returns best move in xy format
        //add code to check all free squares which are valid moves
        int[] highest = new int[32];
        int fill = 0; //where to add new elements if highestValue is identical
        int highestValue = -64;
        for(int y = 1; y <= 8; y++){
            for(int x = 1; x <=8; x++){
                Piece piece = new Piece(x, y, colour);
                if(getScore(piece, check) > highestValue){
                    highestValue = getScore(piece, check);
                    highest = clear(highest, fill);
                    fill = 1;
                    highest[0] = x * 10 + y;
                }
                else if(getScore(piece, check) == highestValue && highestValue != -64){
                    highest[fill] = x * 10 + y;
                    fill++;
                }
            }
        }
        if(getHighestFilled(highest) > 0){
            return choose(highest, fill);
        }
        return highest[0];
    }
    
    public static int choose(int[] chooseFrom, int empty){
        int[] edges = new int[chooseFrom.length];
        int fill = 0;
        for(int iter = 0; iter < empty; iter++){
            if(isCorner(chooseFrom[iter])){
                return chooseFrom[iter]; // preference for corners
            }
            else if(isEdge(chooseFrom[iter])){
                edges[fill] = chooseFrom[iter];
                fill++;
            }
        }
        if(fill == 0){
            Random random = new Random();
            int position = random.nextInt(empty);
            return chooseFrom[position];
        }
        else{
            Random random = new Random();
            int position = random.nextInt(fill);
            return edges[position];
        }
    }
    
    public static boolean isCorner(int check){
        return check == 11 || check == 81 || check == 18 || check == 88;
    }
    
    public static boolean isEdge(int check){
        return check/10 == 8 || check/10 == 1 || check%10 == 8 || check%10 == 1;
    }
    
    public static int[] clear(int[] toClear, int end){
        for(int iter = 0; iter < end; iter++){
            if(toClear[iter] != 0){
                toClear[iter] = 0;
            }
        }
        return toClear;
    }
    
    public static int getScore(Piece check, Board reference){
        int position = Piece.getPosition(check.x, check.y);
        if(reference.pieces[position] != null){
            return -64;
        }
        int score1;
        int[] check1 = Flip.getArray(check.x, check.y, reference, check.colour);
        if(isEmpty(check1) == true){
            return -64;
        }
        score1 = getHighestFilled(check1);
        Piece[] old = clonePieceArray(reference.pieces);
        int score2;
        String otherColour;
        if(check.colour.equals("b")){
            otherColour = "w";
        }
        else{
            otherColour = "b";
        }
        score2 = getHighestControls(reference, otherColour);
        reference.state = "";
        reference.pieces = old;
        return score1 - score2;
    }
    
    public static boolean isEmpty(int[] toCheck){
        for(int iter = 0; iter < toCheck.length; iter++){
            if(toCheck[iter] != 0){
                return false;
            }
        }
        return true;
    }
    
    public static int getHighestFilled(int[] check){
        for(int iter = 0; iter < check.length; iter++){
            if(check[iter] == 0){
                return iter;
            }
        }
        return check.length;
    }
    
    public static int getHighestControls(Board reference, String colour){ //colour is the opposite colour
        int forReturn = -64;
        int score1 = 0;
        int score2 = 0;
        String otherColour;
        if(colour.equals("b")){
            otherColour = "w";
        }
        else{
            otherColour = "b";
        }
        for(int row = 1; row <= 8; row++){
            for(int column = 1; column <= 8; column++){
                if(reference.pieces[Piece.getPosition(column, row)] == null){
                    int compare = getHighestFilled(Flip.getArray(column, row, reference, colour));
                    if(compare > score1){
                        int score1temp = compare;
                        reference = Board.addPiece(reference, new Piece(column, row, colour));
                        int score2temp = 0;
                        for(int y = 1; y <= 8; y++){
                            for(int x = 1; x <= 8; x++){
                                if(getHighestFilled(Flip.getArray(x, y, reference, otherColour)) > score2temp){
                                    score2temp = getHighestFilled(Flip.getArray(x, y, reference, otherColour));
                                }
                            }
                        }
                        if(score1temp - score2temp > forReturn){
                            forReturn = score1temp - score2temp;
                        }
                    }
                }
            }
        }
        return forReturn;
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
}

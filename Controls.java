//Helps find out which piece controls which other squares, contains method to get all the squares in a certain direction controlled by a certain piece

public class Controls
{
    public static int[] getFile(int x, int y, int modX, int modY, int[] forReturn, Board board, String colour){
        int firstNotFilled;
        firstNotFilled = firstNotFilled(forReturn);
        int xAssign;
        int yAssign;
        for(int iter = 0; iter < 8; iter++){
            xAssign = x + (iter + 1) * modX;
            yAssign = y + (iter + 1) * modY;
            if(xAssign > 0 && xAssign < 9 && yAssign > 0 && yAssign < 9 && Board.isOpposite(xAssign, yAssign, board, colour)){
                forReturn[firstNotFilled + iter] = xAssign * 10 + yAssign;
            }
            else if(xAssign > 0 && xAssign < 9 && yAssign > 0 && yAssign < 9 && Board.isSame(xAssign, yAssign, board, colour)){
                return forReturn;
            }
            else{
                for(int iter2 = 0; iter2 <= iter; iter2++){
                    forReturn[firstNotFilled + iter2] = 0;
                }
                return forReturn;
            }
        }
        return forReturn;
    }
    
    public static int firstNotFilled(int[] check){
        for(int iter = 0; iter < check.length; iter++){
            if(check[iter] == 0){
                return iter;
            }
        }
        return -1;
    }
}

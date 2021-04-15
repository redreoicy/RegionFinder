
package regionFinder;
import java.util.*;


public class AreaFinder {
    Square[][] array;
    Set<Square> unCheckedSquares; // a set of 2 element arrays to contain the location of unchecked squares
    Set<Square> currentArea;     // a set of contiguous squares
    Set<Square> maxArea;
    
     int largestAreaFinder(){
        unCheckedSquares = new HashSet<>();
        for (Square[] row : array) { //Add all the squares to the set.
            unCheckedSquares.addAll(Arrays.asList(row));
        }
        
        
        maxArea = new HashSet<>();
        while(!unCheckedSquares.isEmpty()){ //while there are squares left to check
            Square currentSquare = unCheckedSquares.iterator().next(); //get a random unchecked square from the set
            currentArea = new HashSet<>(); //start new current area
            backtrackAreaExpander(currentSquare);
            if(currentArea.size() > maxArea.size()){ //found a bigger area
                maxArea = currentArea;
            }
        }
        System.out.println("The biggest area has " + maxArea.size() + " squares in it");
         System.out.println("The array:");
        for(Square[] row : array){ 
            System.out.println("");
            for(Square sq : row){
                System.out.printf("%2c", sq.value);
            }
        }
        for(Square sq : maxArea){ //X the biggest Area
            array[sq.row][sq.col].value = 'X';
        }
         System.out.println("\n\nThe biggest region:");
        for(Square[] row : array){ 
            System.out.println("");
            for(Square sq : row){
                System.out.printf("%2c", sq.value);
            }
        }
         System.out.println("\n");
         return maxArea.size();
    }
    
    boolean promising(Square currentSquare){//returns true if on a '1', false otherwise
        return currentSquare.value == '1';
    }
    
    void backtrackAreaExpander(Square currentSquare){
         //remove current square from set
        unCheckedSquares.remove(currentSquare);
        if(promising(currentSquare)){ //current square was ok, continue expanding
            
            currentArea.add(currentSquare); //add current to current area
            
            //each branch checks if the square has been checked yet before expanding
            if(currentSquare.row - 1 > -1){
                Square up = array[currentSquare.row -1][currentSquare.col];
                if(unCheckedSquares.contains(up)){ //can go up
                    backtrackAreaExpander(up);
                }
            }
            
            if(currentSquare.row + 1 < array.length){
                Square down = array[currentSquare.row +1][currentSquare.col];
                if(unCheckedSquares.contains(down)){ //can go down
                    backtrackAreaExpander(down);
                }
            }
            
            if(currentSquare.col - 1 > -1){
                Square left = array[currentSquare.row][currentSquare.col-1];
                if(unCheckedSquares.contains(left)){ //can go left
                    backtrackAreaExpander(left);
                }
            }
            
            if(currentSquare.col + 1 < array[0].length){
                Square right = array[currentSquare.row][currentSquare.col + 1];
                if(unCheckedSquares.contains(right)){ //can go right
                    backtrackAreaExpander(right);
                }
            }
            
            if(currentSquare.row - 1 > -1 && currentSquare.col - 1 > -1){
                Square upLeft = array[currentSquare.row -1][currentSquare.col -1];
                if(unCheckedSquares.contains(upLeft)){ //can go upLeft
                    backtrackAreaExpander(upLeft);
                }
            }
            if(currentSquare.row - 1 > -1 && currentSquare.col + 1 < array[0].length){
                Square upRight = array[currentSquare.row -1][currentSquare.col + 1];
                if(unCheckedSquares.contains(upRight)){ //can go upRight
                    backtrackAreaExpander(upRight);
                }
            }
            
            if(currentSquare.row + 1 < array.length && currentSquare.col - 1 > -1){
                Square downLeft = array[currentSquare.row +1][currentSquare.col -1];
                if(unCheckedSquares.contains(downLeft)){ //can go downLeft
                    backtrackAreaExpander(downLeft);
                }
            }
            
            if(currentSquare.row + 1 < array.length && currentSquare.col + 1 < array[0].length){
                Square downRight = array[currentSquare.row +1][currentSquare.col + 1];
                if(unCheckedSquares.contains(downRight)){ //can go downRight
                    backtrackAreaExpander(downRight);
                }
            }
            
        }
        
        
        
    }
    
    public static void main(String[] args) {
        AreaFinder af = new AreaFinder();
        char[] row1 = {'1', '1', '0', '0', '0'};
        char[] row2 = {'0', '1', '1', '0', '0'};
        char[] row3 = {'0', '0', '1', '0', '1'};
        char[] row4 = {'1', '0', '0', '0', '1'};
        char[] row5 = {'0', '1', '0', '1', '1'};
        char[] row6 = {'1', '0', '0', '0', '0'};
        
        af.array = new Square[6][5];
        for (int i = 0; i < 5; i++) {
            af.array[0][i] = new Square(0, i, row1[i]);
        }
        for (int i = 0; i < 5; i++) {
            af.array[1][i] = new Square(1, i, row2[i]);
        }
        for (int i = 0; i < 5; i++) {
            af.array[2][i] = new Square(2, i, row3[i]);
        }
        for (int i = 0; i < 5; i++) {
            af.array[3][i] = new Square(3, i, row4[i]);
        }
        for (int i = 0; i < 5; i++) {
            af.array[4][i] = new Square(4, i, row5[i]);
        }
        for (int i = 0; i < 5; i++) {
            af.array[5][i] = new Square(5, i, row6[i]);
        }
        
        af.largestAreaFinder();
        AreaFinder bigAF = new AreaFinder();
        bigAF.array = new Square[1000][1000];
        Random rng = new Random(235325);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                double val = rng.nextDouble();
                if(val<0.4){
                    bigAF.array[i][j] = new Square(i, j, '1');
                }else{
                    bigAF.array[i][j] = new Square(i, j, '0');
                }
            }
        }
        
        bigAF.largestAreaFinder();
    }
    
}

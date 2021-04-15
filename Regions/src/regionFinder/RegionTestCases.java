package regionFinder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class RegionTestCases {

	@Test
	void test1() {
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
        
        int biggestSize = af.largestAreaFinder();
        assertEquals(5, biggestSize);  //confirm that a size 5 array was found.
	}
	
	@Test
	void test2() {
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
	    
	    int biggestSize = bigAF.largestAreaFinder();
	    assertEquals(26618, biggestSize);
	}

}

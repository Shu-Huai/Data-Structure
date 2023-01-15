package shuhuai.datastructure.string;

import org.junit.Test;

public class StringTest {
    public int rounding(int number) {
        int left = number % 10;
        if (left >= 5) {
            return number + 10 - left;
        } else {
            return number - left;
        }
    }
    @Test
    public void compileTest(){
        System.out.println( rounding(-88));
    }
}

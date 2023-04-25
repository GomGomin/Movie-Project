package ch01;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class cote {

	public static void main(String[] args) throws IOException {

		Solution solution = new Solution();
		int[] numbers = {1,2,3};
		System.out.println(solution.solution(numbers,5));
	}
}

class Solution {
    public int solution(int[] numbers, int k) {
    	return (k-1)*2 % numbers.length+1;
    }
}


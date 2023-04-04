package First_project;

import java.util.HashMap;

public class asdq_asdas {
	public static void main(String[] args) {
		
		
		int[] score = { 90, 85, 78, 100 };

		int sum = 0;
		double avg = 0.0;
		int max = 0;
		int min = 999;

		for (int i : score) {
			sum += i;
			if (max < i)
				max = i;
			if (min > i)
				min = i;
		}
		avg = sum / score.length;
		
		System.out.println("총점 : "+sum);
		System.out.println("평균 : "+avg);
		System.out.println("최대값 : "+max);
		System.out.println("최소값: "+min);
		
		

	}

}

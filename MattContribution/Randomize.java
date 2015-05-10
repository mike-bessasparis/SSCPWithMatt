package com.bessasparis.mike.sscp;

import java.util.*;



public class Randomize {
	
	public static void main(String[] args){
		
		int quizLength = 20;
		
		for (int i = 0; i < 10; i++){
			
			System.out.println(random(quizLength));
			
		}
		
		
	}
	
	private static int random(int quizLength) {
		
		Random rand = new Random();
		
		return rand.nextInt(quizLength);
		
	}

}

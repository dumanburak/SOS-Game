package org;
import java.util.Random;
import java.util.Scanner;

public class Main { // Main Class
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("<-- Welcome to SOS Game! -->\n");
		System.out.println("This game has some rules:\n"
				+ "1) You will determine the panel size. Minimum 5x5 Maximum 10x10.\n"
				+ "2) You will play against computer.\n"
				+ "3) Who will start is decided at random.\n"
				+ "4) In which box you want to enter the letter, you will enter the row, column number and letter from the console\n"
				+ "5) Letters are not allowed in filled boxes.\n"
				+ "<-- HAVE FUN! --> \n");
		
		// Getting input from user to draw matrix
		System.out.println("Please enter minimum 5x5, maximum 10x10 matrix.");
		System.out.print("Enter number of rows: ");
		int row = scan.nextInt();
		System.out.print("Enter number of columns: ");
		int column = scan.nextInt();	
        
		System.out.println();
		if (row < 5 || column < 5 || row > 10 || column > 10) {
			System.out.println("Run the program again and enter valid matrix.");
			System.exit(1);
		}
        
		String[][] matrix = new String [row][column];	// declaring our matrix
		int remainingSpace = row*column;	// null values of matrix
        
		SosGame obj = new SosGame(row, column, remainingSpace);	// creating new object for game
		
		Player player1 = new Player();	// creating new object for player1
		player1.setScore(0);	
		
		Computer computer = new Computer();	// creating new object for player1
		computer.setScore(0);
		
		obj.drawUnderScore(matrix);	// drawing - for all spaces once
		
		Random random = new Random();	// random start (Player1 or Computer)
		int randomStart = random.nextInt(2);
		
		if (randomStart == 0)
			System.out.println("<-- You're going first -->");
		else
			System.out.println("<-- Computer going first -->");
		
		while (obj.remainingSpace > 0) {
			if (randomStart == 0) {
				randomStart = 1;
				System.out.println("Your score is : " + player1.getScore() + " Computer's score is: " + computer.getScore());
				obj.playerTurn(matrix, player1);	// Player's turn
			}else {
				if (obj.remainingSpace <= 0)
					break;
				randomStart = 0;
				System.out.println("Your score is : " + player1.getScore() + " Computer's score is: " + computer.getScore());
				obj.aiTurn(matrix, computer);	// Computer's turn
			}
		}
		
		if (player1.getScore() > computer.getScore()) { // Final Scores and Who's win
			System.out.println("<-- YOU WON ! -->");
			System.out.println("Your score is : " + player1.getScore() + " Computer's score is: " + computer.getScore());
		}
		else if (player1.getScore() == computer.getScore()) {
			System.out.println("<-- DRAW -->");
			System.out.println("Your score is : " + player1.getScore() + " Computer's score is: " + computer.getScore());
		}
		else {
			System.out.println("<-- COMPUTER WON ! -->");
			System.out.println("Your score is : " + player1.getScore() + " Computer's score is: " + computer.getScore());
		}
	}

}

import java.util.Random;
import java.util.Scanner;

interface Game {	// Functions of the SOS-Game
	public void drawUnderScore(String[][] matrix);
	public void draw(String[][] matrix);
	public void insert(String [][] matrix, int row, int column, String letter);
	public void playerTurn (String[][] matrix, Object player1);
	public void aiTurn (String[][] matrix, Object computer);
	public void checkPoint(String[][] matrix, Object player, int chosenRow, int chosenColumn, String letter);
}

public class SosGame implements Game{
	int row, column, remainingSpace;
	int columnController = 0;	// for the draw() function to switch to next line
	
	public SosGame(int row, int column, int remainingSpace) {	// Game Constructor
		this.row = row;
		this.column = column;
		this.remainingSpace = remainingSpace;
	}
	
	public void drawUnderScore(String[][] matrix) {	//Drawing all of the places with - symbol.

        for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j]="-";
                }
            }
    	draw(matrix);
        System.out.println();
	}
	
	public void draw(String[][] matrix) {	// Drawing spaces
		
		System.out.print("\n    ");
		for (int i = 0; i < column; i++) {
			System.out.print(i+1);
			System.out.print("   ");
		}System.out.println("\n");

        for (int x = 0; x < row; x++) {
        	System.out.print(x+1);
            for (int y = 0; y < column; y++) {
                System.out.print("   "+ matrix[x][y]);
                columnController++;
                if (columnController == column) {
                    System.out.println("\n   ");
                    columnController = 0;
                }
            }
        }
	}
	
	public void insert(String [][] matrix, int row, int column, String letter){	// inserting input value (S or O)
		matrix[row][column] = letter;
		draw(matrix);
	}
	
	public void playerTurn (String[][] matrix, Object player1) {	// Player's turn
		
		if (remainingSpace <= 0)
			return;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Select a row: ");
		int chosenRow = scan.nextInt();
		
		System.out.print("Select a column: ");
		int chosenColumn = scan.nextInt();
		
		System.out.print("Enter S or O: ");
		String letter = scan.next();
		
		if (!letter.equals("s") && !letter.equals("o") && !letter.equals("S") && !letter.equals("O")) {
			System.out.println("\n--> Invalid input. Please enter S or O (not case-sensivite) <--\n");
			playerTurn(matrix, player1);
		}
		
		if (matrix[chosenRow-1][chosenColumn-1] == "-") {
        	remainingSpace--;
        	insert(matrix, chosenRow-1, chosenColumn-1, letter.toUpperCase());
        	checkPoint(matrix, player1, chosenRow-1, chosenColumn-1, letter.toUpperCase());
        }else {
        	System.out.println("\n--> This area is already full, please enter valid row and column. <--\n");
        	playerTurn(matrix, player1);
        }
		
		
	}
	
	public void aiTurn (String[][] matrix, Object computer) {	// Computer's Turn
		Random random = new Random();
		int randomRow = random.nextInt(5);
		int randomColumn = random.nextInt(5);
		
		String letters ="OS";
        String[] part = letters.split("");
        Random random1 = new Random();
        int selectValueIndex = random1.nextInt((1-0)+1)+0;
        
        if (matrix[randomRow][randomColumn] == "-") {
        	remainingSpace--;
            insert(matrix, randomRow, randomColumn, part[selectValueIndex]);
            checkPoint(matrix, computer, randomRow, randomColumn, part[selectValueIndex]);
        }else {
        	if (remainingSpace > 0)
        		aiTurn(matrix, computer);
        }
	}
	
	public void checkPoint(String[][] matrix, Object player, int chosenRow, int chosenColumn, String letter) { // Checking Point Function
		float mid = (matrix.length + 1) / 2;	// Getting mid of our matrix

		// For S letter
		if (letter.equals("S") && remainingSpace > 0) {
			if (chosenRow+1 <= mid && chosenColumn+1 <= mid) { // top left
				if (matrix[chosenRow][chosenColumn+1].equals("O") && matrix[chosenRow][chosenColumn+2].equals("S")) { 
					((Player) player).addScore(1);	// Adding 1 score while matching SOS
					playerTurn(matrix, player); 	// Player has one more turn
					return;
				}
				else if (matrix[chosenRow+1][chosenColumn].equals("O") && matrix[chosenRow+2][chosenColumn].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow+1][chosenColumn+1].equals("O") && matrix[chosenRow+2][chosenColumn+2].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
			}
			
			if (chosenRow+1 <= mid && chosenColumn+1 >= mid) { // top right
				if (matrix[chosenRow][chosenColumn-1].equals("O") && matrix[chosenRow][chosenColumn-2].equals("S")) { 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow+1][chosenColumn].equals("O") && matrix[chosenRow+2][chosenColumn].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow+1][chosenColumn-1].equals("O") && matrix[chosenRow+2][chosenColumn-2].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
			}
		
		
			if (chosenRow+1 >= mid && chosenColumn+1 <= mid) { // bottom left
				if (matrix[chosenRow][chosenColumn+1].equals("O") && matrix[chosenRow][chosenColumn+2].equals("S")) { 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow-1][chosenColumn].equals("O") && matrix[chosenRow-2][chosenColumn].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow-1][chosenColumn+1].equals("O") && matrix[chosenRow-2][chosenColumn+2].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
			}
		
			if (chosenRow+1 >= mid && chosenColumn+1 >= mid) { // bottom right
				if (matrix[chosenRow][chosenColumn-1].equals("O") && matrix[chosenRow][chosenColumn-2].equals("S")) { 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow-1][chosenColumn].equals("O") && matrix[chosenRow-2][chosenColumn].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
				else if (matrix[chosenRow-1][chosenColumn-1].equals("O") && matrix[chosenRow-2][chosenColumn-2].equals("S")) {
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
				}
			}
		}
		// For O letter
		else if (letter.equals("O") && remainingSpace > 0) { 
			if (chosenRow+1 == 1 && chosenColumn+1 == 1 || chosenRow+1 == 1 && chosenColumn+1 == column || chosenRow+1 == row && chosenColumn+1 == 1 || chosenRow+1 == row && chosenColumn+1 == column) {
				return;
			}
			else if (chosenColumn+1 == 1 || chosenColumn+1 == column) {
				if (matrix[chosenRow-1][chosenColumn].equals("S") && matrix[chosenRow+1][chosenColumn].equals("S")) { 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
				}
				return;
			}
			else if (chosenRow+1 == 1 || chosenRow+1 == row) {
				if (matrix[chosenRow][chosenColumn-1].equals("S") && matrix[chosenRow][chosenColumn+1].equals("S")) { 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
				}
				return;
			}
			else if (matrix[chosenRow][chosenColumn-1].equals("S") && matrix[chosenRow][chosenColumn+1].equals("S") || // soldan sağa
					matrix[chosenRow-1][chosenColumn].equals("S") && matrix[chosenRow+1][chosenColumn].equals("S") || // yukarıdan aşağıya
					matrix[chosenRow-1][chosenColumn-1].equals("S") && matrix[chosenRow+1][chosenColumn+1].equals("S") || // sol çapraz  
					matrix[chosenRow-1][chosenColumn+1].equals("S") && matrix[chosenRow+1][chosenColumn-1].equals("S")) // sağ çapraz 
					{ 
					((Player) player).addScore(1);
					playerTurn(matrix, player); 
					return;
			}
		}
	}
}

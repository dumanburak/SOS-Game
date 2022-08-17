
public class Player { // Player class and get set functions
	private int score;
	private String name;

	public int getScore() {
        return score;
    }
	
	public String getName() {
		return name;
	}

    public void addScore(int score) {
        this.score += score ;
    }
    
    public void setScore(int score) {
    	this.score = score;
    }
}

class Computer extends Player {	// Computer class and inherits Player's variables and functions
	
}
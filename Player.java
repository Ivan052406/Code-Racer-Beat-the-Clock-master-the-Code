import java.util.Scanner;
//Enncapsulation
public class Player {
    private String name;
    private int score;
    private int totalScore;
    private String level;
    private static int playerCount = 0;

    Scanner sc = new Scanner (System.in);

    public Player(String name) {
        setName(name);
        this.score = 0;
        this.totalScore = 0;
        this.level = "Easy";
        playerCount++;
    }

    public Player(String name, int score, String level) {;
        //setters
        setName(name);
        setScore(score);
        setLevel(level);
        this.totalScore = score;
        playerCount++;
    }
     String green = "\u001B[32m";
     //getters

    public String getName() { 
        return name; 
    }
    public int getScore() { 
        return score; 
    }
    public int getTotalScore() { 
        return totalScore;
    }
    public String getLevel() { 
        return level; 
    }
    public static int getPlayerCount() { 
        return playerCount; 
    }

    public void setName(String name) {
    
        String playerName = name; 

       
while (true) {
    System.out.print(green +"Enter your name (at least 2 characters): ");
    playerName = sc.nextLine().trim(); 

    if (playerName.length() >= 2) {
    this.name = playerName.trim();
        break;
    } else {
        System.out.println("Invalid name! Try again.");
    }
}
}
//setters
    public void setScore(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        this.score = score;
    }

    public void addScore(int points) {
    this.score = points;        
    this.totalScore += points; 
}

    public void setLevel(String level) {
        switch (level) {
            case "Easy":
            case "Medium":
            case "Hard":
                this.level = level;
                break;
            default:
                this.level = "Easy";
                System.out.println("Invalid level. Defaulting to Easy.");
        }
    }


    public void resetScore() {
        this.score = 0;
        this.totalScore = 0;
    }


}
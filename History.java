public class History { 

    // We get the variables in the class player
    private Player player;
    private String language; 
    private String quizType; 
    private int score; 
    private int totalQuestions; 
    public History(Player player, String language, String quizType, int score, int totalQuestions) { 
        this.player = player; 
        this.language = language; 
        this.quizType = quizType; 
        this.score = score; 
        this.totalQuestions = totalQuestions; 
    } 
    public Player getPlayer() { 
        return player; 
    } 
    public String getLanguage() { 
        return language;
     } 
    public String getQuizType() {
         return quizType; 
        } 
    public int getScore() { 
            return score; 
        } 
    public int getTotalQuestions() { 
            return totalQuestions; 
        }
     }
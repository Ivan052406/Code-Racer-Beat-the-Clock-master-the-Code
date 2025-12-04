import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
// IMPORTS: All imports are from java.util package for various utilities:
// ArrayList - for dynamic lists (quiz history storage)
// Arrays - for array utilities (converting arrays to lists)
// Collections - for collection operations (shuffling questions)
// List - interface for ordered collections (question lists)
// Random - for generating random numbers (shuffling and selection)

class Question {
    String question; 
    String[] options; 
    char answer;     


    Question(String question, String[] options, char answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }
}

    /**
 * Abstract class for multiple-choice quiz rounds.
 * Demonstrates ABSTRACTION (hiding implementation details) and INHERITANCE.
 * Uses TEMPLATE METHOD PATTERN - defines algorithm structure in startQuiz(),
 * while letting subclasses implement specific parts (getQuestions(), displayResults()).
 */

abstract class QuizRound{
    protected Question[] questions;
    protected Scanner sc;
    protected Player player;
    protected Random random = new Random();

    public QuizRound(Scanner sc, Player player) {
        this.sc = sc;
        this.player = player;
        this.questions = getQuestions();
    }
/**
     * Abstract method that subclasses must implement.
     * Returns array of questions for the specific quiz type.
     * return Array of Question objects
     */

    protected abstract Question[] getQuestions();

    public int startQuiz() {

    List<Question> questionList = Arrays.asList(questions);
    Collections.shuffle(questionList, random);
    questions = questionList.toArray(new Question[0]);

    TimerQuiz tq = new TimerQuiz();
    int timeLimit = tq.chooseDifficulty();
    
    int roundScore = 0;  
   
    
    for (int i = 0; i < questions.length; i++) {
        Question q = questions[i];
 // ANSI color codes for colored output

        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";

        System.out.println(red + "\nQ" + (i + 1) + ": "+cyan + q.question);
        System.out.println(red +"A. "+ cyan+ q.options[0]);
        System.out.println(red +"B. "+ cyan + q.options[1]);
        System.out.println(red +"C. "+ cyan + q.options[2]);
        System.out.println(red +"D. "+ cyan + q.options[3]);


  char answer = tq.startTimer(timeLimit);

if (answer == ' ') { 
    System.out.println(red +"\n Time's up! Moving to next question.");
    continue;
    } 

if (answer == q.answer) {
            System.out.println(green +"Correct");
            roundScore++;
        } else {
            System.out.println(red +"Wrong! Correct answer: " + q.answer);
        }
    }


    player.addScore(roundScore);

    displayResults(roundScore);
    return roundScore;
}


    protected abstract void displayResults(int roundScore);
    public static char safeCharInput(Scanner sc) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        while (true) {
            System.out.print(cyan +"Your answer (A-D): ");
            String input = sc.next().toUpperCase();

            if (input.matches("[A-D]")) {
                return input.charAt(0);
            }

            System.out.println(red +"Invalid input! Enter A, B, C, or D only.");
        }
    }
}
abstract class WrittenQuiz {
    protected List<WrittenQuestion> questions;
    protected Scanner sc;
    protected Player player;
    protected Random random = new Random();

    public WrittenQuiz(Scanner sc, Player player) {
        this.sc = sc;
        this.player = player;
        this.questions = getQuestions();
    }

    protected abstract List<WrittenQuestion> getQuestions();
    protected abstract String getQuizName();

    public int startQuiz() {

        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";

        Collections.shuffle(questions, random);
        int score = 0;

        System.out.println("\n===== " + getQuizName() + " =====");

        for (int i = 0; i < questions.size(); i++) {
            WrittenQuestion q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.getQuestion());
            System.out.print(cyan +"Your answer: ");
             String ans = sc.nextLine();
           
        

            if (q.checkAnswer(ans)) {
                System.out.println(green +"Correct!");
                score++;
            } else {
                System.out.println(red +"Wrong! Correct answer: " + q.getAnswer());
            }
        }

        player.addScore(score);
        System.out.println(cyan +"\nScore: " + score + "/" + questions.size());
        return score;
    }
}
//using extends keyword to demonstrace inheritance
class PythonWrittenQuiz extends WrittenQuiz {

    public PythonWrittenQuiz(Scanner sc, Player player) {
        super(sc, player); 
    }
//using override that represents polymorphysm
    @Override
    protected List<WrittenQuestion> getQuestions() {
        return Arrays.asList(
            new WrittenQuestion("What keyword is used to define a function?", "def"),
            new WrittenQuestion("Python immutable sequence type?", "tuple"),
            new WrittenQuestion("Symbol used for single-line comment?", "#"),
            new WrittenQuestion("Python file extension?", ".py"),
            new WrittenQuestion("Keyword for exception handling?", "try"),
            new WrittenQuestion("Output of 2 ** 3?", "8"),
            new WrittenQuestion("Function to get user input?", "input"),
            new WrittenQuestion("Which data type is mutable?", "list"),
            new WrittenQuestion("Keyword to create a class?", "class"),
            new WrittenQuestion("Equality operator in Python?", "=="),
            new WrittenQuestion("How to check type of a variable?", "type"),
            new WrittenQuestion("Keyword to handle errors?", "except"),
            new WrittenQuestion("Module for random numbers?", "random"),
            new WrittenQuestion("Symbol for floor division?", "//"),
            new WrittenQuestion("Keyword for infinite loop?", "while")
        );
    }

    @Override
    protected String getQuizName() {
        return "Python Written Quiz";
    }
}

class JavaWrittenQuiz extends WrittenQuiz {

    public JavaWrittenQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected List<WrittenQuestion> getQuestions() {
        return Arrays.asList(
            new WrittenQuestion("Entry point method of a Java program?", "main"),
            new WrittenQuestion("Keyword to create a subclass?", "extends"),
            new WrittenQuestion("Single-line comment symbol?", "//"),
            new WrittenQuestion("Default boolean value?", "false"),
            new WrittenQuestion("Keyword to prevent inheritance?", "final"),
            new WrittenQuestion("Class used for dynamic arrays?", "ArrayList"),
            new WrittenQuestion("Equality operator in Java?", "=="),
            new WrittenQuestion("Keyword for exception handling?", "try"),
            new WrittenQuestion("Keyword to create object?", "new"),
            new WrittenQuestion("JVM stands for?", "Java Virtual Machine"),
            new WrittenQuestion("Method overloading means?", "same name, diff params"),
            new WrittenQuestion("Loop that runs at least once?", "do-while"),
            new WrittenQuestion("Public members are visible to?", "everyone"),
            new WrittenQuestion("Keyword to define a class?", "class"),
            new WrittenQuestion("Package used for lists?", "java.util")
        );
    }

    @Override
    protected String getQuizName() {
        return "Java Written Quiz";
    }
}

class CppWrittenQuiz extends WrittenQuiz {

    public CppWrittenQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected List<WrittenQuestion> getQuestions() {
        return Arrays.asList(
            new WrittenQuestion("Header for cout and cin?", "<iostream>"),
            new WrittenQuestion("Start of main function?", "int main()"),
            new WrittenQuestion("Single-line comment symbol?", "//"),
            new WrittenQuestion("Pointer dereference operator?", "*"),
            new WrittenQuestion("Output of 5 / 2?", "2"),
            new WrittenQuestion("OOP stands for?", "Object-Oriented Programming"),
            new WrittenQuestion("How to create an object?", "class"),
            new WrittenQuestion("Syntax to include library?", "#include <>"),
            new WrittenQuestion("Loop that runs at least once?", "do-while"),
            new WrittenQuestion("Keyword for constant?", "const"),
            new WrittenQuestion("Equality operator?", "=="),
            new WrittenQuestion("Function overloading means?", "same name, diff params"),
            new WrittenQuestion("new keyword does?", "allocates memory"),
            new WrittenQuestion("Purpose of constructor?", "initialize object"),
            new WrittenQuestion("Output of cout << true?", "1")
        );
    }

    @Override
    protected String getQuizName() {
        return "C++ Written Quiz";
    }
}

class PythonQuiz extends QuizRound { 
    public PythonQuiz(Scanner sc, Player player) { 
    super(sc, player); }


    @Override
    protected Question[] getQuestions() {
        return new Question[]{
                new Question("What keyword is used to define a function?", new String[]{"func", "define", "def", "fn"}, 'C'),
                new Question("What is the output of len(\"Hello\")?", new String[]{"4", "5", "6", "Error"}, 'B'),
                new Question("Which is an immutable data type?", new String[]{"List", "Dictionary", "Tuple", "Set"}, 'C'),
                new Question("Which symbol is used for comments?", new String[]{"//", "#", "/* */", "<!-- -->"}, 'B'),
                new Question("Python file extension?", new String[]{".pt", ".py", ".pyt", ".pyc"}, 'B'),
                new Question("What is 2 ** 3?", new String[]{"5", "6", "8", "9"}, 'C'),
                new Question("input() returns what data type?", new String[]{"int", "string", "bool", "float"}, 'B'),
                new Question("Keyword for exception handling?", new String[]{"catch", "try", "except", "handle"}, 'B'),
                new Question("type(3.5) returns?", new String[]{"int", "float", "double", "string"}, 'B'),
                new Question("Which creates a list?", new String[]{"{}", "()", "[]", "<>"}, 'C'),
                new Question("What does break do?", new String[]{"Skips iteration", "Ends program", "Exits loop", "Continues loop"}, 'C'),
                new Question("Equality operator?", new String[]{"=", "==", "!=", "is"}, 'B'),
                new Question("Dictionary stores?", new String[]{"keys only", "values only", "key-value pairs", "numbers"}, 'C'),
                new Question("Keyword for defining a class?", new String[]{"struct", "class", "object", "new"}, 'B'),
                new Question("Module for random numbers?", new String[]{"random", "math", "numbers", "rand"}, 'A')
        };
    }

    @Override
    protected void displayResults(int roundScore) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        System.out.println(green +"\n===== Python Quiz Complete =====");
        System.out.println("Round score: " + roundScore + "/" + questions.length);
        System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
        System.out.println(cyan +"Python mastery level: " + (roundScore >= 10 ? green + "Expert" : red +"Beginner"));
    }
}

class JavaQuiz extends QuizRound {
    public JavaQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected Question[] getQuestions() {
        return new Question[]{
                new Question("Entry point of Java program?",
                        new String[]{"main()", "start()", "run()", "public static void main(String[] args)"}, 'D'),
                new Question("Keyword to create a subclass?",
                        new String[]{"inherits", "extends", "implements", "super"}, 'B'),
                new Question("Size of int?",
                        new String[]{"2 bytes", "4 bytes", "8 bytes", "16 bytes"}, 'B'),
                new Question("Single-line comment?",
                        new String[]{"//", "#", "<!--", "/* */"}, 'A'),
                new Question("Default boolean value?",
                        new String[]{"true", "false", "0", "null"}, 'B'),
                new Question("Keyword preventing inheritance?",
                        new String[]{"static", "final", "sealed", "const"}, 'B'),
                new Question("ArrayList is part of?",
                        new String[]{"Java Utils", "Java Collection", "JFrame", "JDBC"}, 'B'),
                new Question("Equality operator?",
                        new String[]{"=", "==", "===", "!=="}, 'B'),
                new Question("Keyword for exception handling?",
                        new String[]{"catch", "error", "try", "throw"}, 'C'),
                new Question("Void method returns?",
                        new String[]{"int", "null", "0", "nothing"}, 'D'),
                new Question("Keyword to create an object?",
                        new String[]{"class", "object", "new", "import"}, 'C'),
                new Question("JVM stands for?",
                        new String[]{"Java Very Machine", "Java Virtual Method", "Java Virtual Machine", "Java Version Module"}, 'C'),
                new Question("Method overloading means?",
                        new String[]{"same name, diff params", "same name, same params", "diff name, same body", "diff file"}, 'A'),
                new Question("Loop that runs at least once?",
                        new String[]{"for", "while", "do-while", "foreach"}, 'C'),
                new Question("Public members are visible to?",
                        new String[]{"Same file only", "Same package", "Class only", "Everyone"}, 'D')
        };
    }

    @Override
    protected void displayResults(int roundScore) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        System.out.println(green +"\n===== Java Quiz Complete =====");
        System.out.println("Round score: " + roundScore + "/" + questions.length);
        System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
        System.out.println(cyan +"Java mastery level: " + (roundScore >= 10 ? green +"Expert" : red +"Beginner"));
    }
}

class CppQuiz extends QuizRound {
    public CppQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected Question[] getQuestions() {
        return new Question[]{
                new Question("Header for cout and cin?",
                        new String[]{"<stdio>", "<streams>", "<iostream>", "<output>"}, 'C'),
                new Question("Start of main function?",
                        new String[]{"void main()", "main()", "int main()", "start()"}, 'C'),
                new Question("Single-line comment?",
                        new String[]{"#", "//", "/* */", "@"}, 'B'),
                new Question("Pointer dereference operator?",
                        new String[]{"&", "*", "@", "#"}, 'B'),
                new Question("Output of 5 / 2?",
                        new String[]{"2", "2.5", "3", "5"}, 'A'),
                new Question("OOP stands for?",
                        new String[]{"Open Object Program", "Object-Oriented Programming", "Operation-Oriented Process", "Output Processing"}, 'B'),
                new Question("How to create an object?",
                        new String[]{"struct", "class", "declare variable of class", "malloc"}, 'C'),
                new Question("Syntax to include library?",
                        new String[]{"import<>", "#include <>" , "#include <>", "load<>"} , 'C'),
                new Question("Loop that runs at least once?",
                        new String[]{"for", "while", "do-while", "repeat"}, 'C'),
                new Question("Keyword for constant?",
                        new String[]{"constant", "def", "const", "static"}, 'C'),
                new Question("Equality operator?",
                        new String[]{"=", "==", "!=", "<>"}, 'B'),
                new Question("Function overloading means?",
                        new String[]{"Same name, diff params", "Diff name, same params", "Same name, same params", "Diff name"}, 'A'),
                new Question("new keyword does?",
                        new String[]{"Deletes memory", "Allocates memory", "Creates class", "Opens file"}, 'B'),
                new Question("Output of cout << true?",
                        new String[]{"true", "1", "0", "yes"}, 'B'),
                new Question("Purpose of constructor?",
                        new String[]{"Destroy object", "Copy object", "Initialize object", "Free memory"}, 'C')
        };
    }

    @Override
    protected void displayResults(int roundScore) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        System.out.println(green +"\n===== C++ Quiz Complete =====");
        System.out.println("Round score: " + roundScore + "/" + questions.length);
        System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
        System.out.println(cyan +"C++ mastery level: " + (roundScore >= 10 ? green +"Expert" : red +"Beginner"));
    }
}

class MixedQuiz extends QuizRound {
    public MixedQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected Question[] getQuestions() {

        Question[] pythonQs = new PythonQuiz(sc, player).getQuestions();
        Question[] javaQs = new JavaQuiz(sc, player).getQuestions();
        Question[] cppQs = new CppQuiz(sc, player).getQuestions();

        Question[] allQs = new Question[pythonQs.length + javaQs.length + cppQs.length];
        System.arraycopy(pythonQs, 0, allQs, 0, pythonQs.length);
        System.arraycopy(javaQs, 0, allQs, pythonQs.length, javaQs.length);
        System.arraycopy(cppQs, 0, allQs, pythonQs.length + javaQs.length, cppQs.length);

        Question[] selected = new Question[10];
        for (int i = 0; i < 10; i++) {
            selected[i] = allQs[random.nextInt(allQs.length)];
        }
        return selected;
    }

    @Override
    protected void displayResults(int roundScore) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        System.out.println(green +"\n===== Mixed Quiz Complete =====");
        System.out.println("Round score: " + roundScore + "/" + questions.length);
        System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
        System.out.println(cyan +"Mixed mastery level: " + (roundScore >= 7 ?green + "Expert" : red + "Beginner"));
    }
}

class MixedWrittenQuiz extends WrittenQuiz {
    public MixedWrittenQuiz(Scanner sc, Player player) {
        super(sc, player);
    }

    @Override
    protected List<WrittenQuestion> getQuestions() {
        List<WrittenQuestion> pythonQs = new PythonWrittenQuiz(sc, player).getQuestions();
        List<WrittenQuestion> javaQs = new JavaWrittenQuiz(sc, player).getQuestions();
        List<WrittenQuestion> cppQs = new CppWrittenQuiz(sc, player).getQuestions();

    
        List<WrittenQuestion> allQs = new ArrayList<>();
        allQs.addAll(pythonQs);
        allQs.addAll(javaQs);
        allQs.addAll(cppQs);

    
        Collections.shuffle(allQs, random);
        return allQs.subList(0, Math.min(10, allQs.size()));
    }

    @Override
    protected String getQuizName() {
        return "Mixed Written Quiz";
    }

    
    @Override
    public int startQuiz() {
        int score = super.startQuiz();
        displayMixedResults(score);
        return score;
    }

    private void displayMixedResults(int roundScore) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        System.out.println(green +"\n===== Mixed Written Quiz Complete =====");
        System.out.println("Round score: " + roundScore + "/" + questions.size());
        System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
        System.out.println(cyan +"Mixed mastery level: " + (roundScore >= 7 ?green + "Expert" : red + "Beginner"));
    }
}




public class GameOfQuiz {
    private List<History> historyList = new ArrayList<>();
    private Player player;
    private Scanner sc = new Scanner(System.in);
    String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";

    public GameOfQuiz(Player player) {
        this.player = player;
    }

    public void start() {
        while (true) {
            System.out.println(green +"\n===== CODE RACER =====");
            System.out.println(cyan +"Main Menu:");
            System.out.println(cyan +"1. Start Quiz");
            System.out.println(cyan +"2. View History");
            System.out.println(cyan +"3. Exit");
            
            int mainChoice = safeIntInput(sc, 1, 3);
            
            if (mainChoice == 1) {
                startQuiz();
            } else if (mainChoice == 2) {
                viewHistory();
            } else {
                break;
            }
        }

        System.out.println(cyan +"\nThanks for playing, " + player.getName() + "! Your final total score: " + player.getTotalScore());
    }

    private void startQuiz() {
        System.out.println(green +"\nChoose quiz type:");
        System.out.println(cyan +"1. Multiple Choice (with timer)");
        System.out.println(cyan +"2. Identification and Fill-in-the-blank (without timer)");

        int typeChoice = safeIntInput(sc, 1, 2);

        System.out.println(green +"\nChoose a language:");
        System.out.println(cyan +"1. Python");
        System.out.println(cyan +"2. Java");
        System.out.println(cyan +"3. C++");
        System.out.println(cyan +"4. Mixed");

        int langChoice = safeIntInput(sc, 1, 4);

        int roundScore = 0;
        History entry = null;

        if (typeChoice == 1) {
            QuizRound selectedQuiz;
            if (langChoice == 1) selectedQuiz = new PythonQuiz(sc, player);
            else if (langChoice == 2) selectedQuiz = new JavaQuiz(sc, player);
            else if (langChoice == 3) selectedQuiz = new CppQuiz(sc, player);
            else selectedQuiz = new MixedQuiz(sc, player);

            roundScore = selectedQuiz.startQuiz();
            String language = (langChoice == 1) ? "Python" : (langChoice == 2) ? "Java" : 
                             (langChoice == 3) ? "C++" : "Mixed";
            String quizType = "Multiple Choice";
            entry = new History(player, language, quizType, roundScore, selectedQuiz.getQuestions().length);
            historyList.add(entry);  
        } else {
            WrittenQuiz writtenQuiz;
            if (langChoice == 1) writtenQuiz = new PythonWrittenQuiz(sc, player);
            else if (langChoice == 2) writtenQuiz = new JavaWrittenQuiz(sc, player);
            else if (langChoice == 3) writtenQuiz = new CppWrittenQuiz(sc, player);
            else writtenQuiz = new MixedWrittenQuiz(sc, player);

            roundScore = writtenQuiz.startQuiz();
            String language = (langChoice == 1) ? "Python" : (langChoice == 2) ? "Java" : 
                             (langChoice == 3) ? "C++" : "Mixed";
            String quizType = "Written";
            entry = new History(player, language, quizType, roundScore, writtenQuiz.getQuestions().size());
            historyList.add(entry);
        }

    
        System.out.println(green +"\n===== QUIZ COMPLETED =====");
        System.out.println(cyan +"Score: " + roundScore + "/" + entry.getTotalQuestions());
        System.out.println("Accuracy: " + String.format("%.1f%%", (roundScore * 100.0 / entry.getTotalQuestions())));
    }

    private void viewHistory() {
        if (historyList.isEmpty()) {
            System.out.println(green +"\n===== QUIZ HISTORY =====");
            System.out.println(cyan +"No quiz history available yet.");
            System.out.println(cyan +"Play some quizzes to see your history here!");
            System.out.println(cyan +"\nPress Enter to continue...");
            sc.nextLine();
            return;
        }

        System.out.println(green +"\n===== QUIZ HISTORY =====");
        System.out.println(cyan +"Player: " + player.getName());
        System.out.println(cyan +"Total Score: " + player.getTotalScore());
        System.out.println(cyan +"Total Quizzes Taken: " + historyList.size());
        System.out.println(cyan +"\nQuiz Details:");
        System.out.println("------------------------------------------------------------");
        
        int totalCorrect = 0;
        int totalQuestions = 0;
        
        for (int i = 0; i < historyList.size(); i++) {
            History entry = historyList.get(i);
            System.out.println((i + 1) + ". " + entry.getLanguage() + " - " + entry.getQuizType());
            System.out.println("   Score: " + entry.getScore() + "/" + entry.getTotalQuestions());
            double percentage = (entry.getScore() * 100.0 / entry.getTotalQuestions());
            System.out.println("   Percentage: " + String.format("%.1f%%", percentage));
            System.out.println("------------------------------------------------------------");
            
            totalCorrect += entry.getScore();
            totalQuestions += entry.getTotalQuestions();
        }

    
        showHistoryStatistics(totalCorrect, totalQuestions);
        
        System.out.println(cyan +"\nPress Enter to continue...");
        sc.nextLine();
    }

    private void showHistoryStatistics(int totalCorrect, int totalQuestions) {
        System.out.println(cyan +"\n===== STATISTICS =====");
        System.out.println(cyan +"Overall Accuracy: " + String.format("%.1f%%", 
            (totalCorrect * 100.0 / totalQuestions)));
        
        int pythonQuizzes = 0, javaQuizzes = 0, cppQuizzes = 0, mixedQuizzes = 0;
        int mcQuizzes = 0, writtenQuizzes = 0;
        
        for (History entry : historyList) {
    
            switch (entry.getLanguage()) {
                case "Python": pythonQuizzes++; break;
                case "Java": javaQuizzes++; break;
                case "C++": cppQuizzes++; break;
                case "Mixed": mixedQuizzes++; break;
            }
            
        
            if (entry.getQuizType().equals("Multiple Choice")) {
                mcQuizzes++;
            } else {
                writtenQuizzes++;
            }
        }
        
        System.out.println("\nQuizzes by Language:");
        System.out.println("  Python: " + pythonQuizzes);
        System.out.println("  Java: " + javaQuizzes);
        System.out.println("  C++: " + cppQuizzes);
        System.out.println("  Mixed: " + mixedQuizzes);
        System.out.println("\nQuizzes by Type:");
        System.out.println("  Multiple Choice: " + mcQuizzes);
        System.out.println("  Written: " + writtenQuizzes);
        
    
        if (!historyList.isEmpty()) {
            History bestQuiz = historyList.get(0);
            History worstQuiz = historyList.get(0);
            
            for (History entry : historyList) {
                double currentPercentage = entry.getScore() * 100.0 / entry.getTotalQuestions();
                double bestPercentage = bestQuiz.getScore() * 100.0 / bestQuiz.getTotalQuestions();
                double worstPercentage = worstQuiz.getScore() * 100.0 / worstQuiz.getTotalQuestions();
                
                if (currentPercentage > bestPercentage) {
                    bestQuiz = entry;
                }
                if (currentPercentage < worstPercentage) {
                    worstQuiz = entry;
                }
            }
            
            System.out.println(green +"\nBest Performance:");
            System.out.println("  " + bestQuiz.getLanguage() + " - " + bestQuiz.getQuizType() + 
                             " (" + String.format("%.1f%%", bestQuiz.getScore() * 100.0 / bestQuiz.getTotalQuestions()) + ")");
            System.out.println(red +"\nWorst Performance:");
            System.out.println("  " + worstQuiz.getLanguage() + " - " + worstQuiz.getQuizType() + 
                             " (" + String.format("%.1f%%", worstQuiz.getScore() * 100.0 / worstQuiz.getTotalQuestions()) + ")");
        }
    }

    public static int safeIntInput(Scanner sc, int min, int max) {
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        int input = -1;
        while (true) {
            try {
                System.out.print(cyan +"Enter choice: ");
                input = sc.nextInt();
                sc.nextLine();
                if (input < min || input > max) {
                    System.out.println(red +"Invalid choice! Enter between " + min + " and " + max);
                } else break;
            } catch (Exception e) {
                System.out.println(red +"Invalid input! Numbers only.");
                sc.nextLine();
            }
        }
        return input;
    }
}
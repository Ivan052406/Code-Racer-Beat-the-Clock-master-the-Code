import java.util.Scanner;
public class algoRacers{

public static void clearScreen() {
            for (int i = 0; i < 30; i++)
            System.out.println();
        }
        
    

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);

        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        
        System.out.println(red +"                                     ████████  ████████  ██████     ██████" +green +"        █████       ██      ███████  ██████  █████");
        System.out.println(red +"                                     ██        ██    ██  ██   ███   ██    " +green +"        ██  ██     █  █     ██       ██      ██  ██");
        System.out.println(red +"                                     ██        ██    ██  ██    ███  ██████  ██" +green +"██  █████     ██████    ██       ██████  █████");
        System.out.println(red +"                                     ██        ██    ██  ██   ███   ██    " +green +"        ██  ██   ██    ██   ██       ██      ██  ██");
        System.out.println(red +"                                     ████████  ████████  ██████     ██████" +green +"        ██  ██  ██      ██  ███████  ██████  ██  ██");
        System.out.println(cyan +"                                                                  Press Enter to Continue");
        input.nextLine();
        clearScreen();


        System.out.println(green +"  ===================================================================================================================================================================");
        System.out.println(cyan +"  ||===============================================================================================================================================================||");
        System.out.println(cyan +"  ||                                                                   Welcome to Code Racer!                                                                      ||");
        System.out.println(cyan +"  ||                                                 A fast-paced programming quiz game where you race against                                                     ||");
        System.out.println(cyan +"  ||                                                   time to answer questions about Java, Python, and C++.                                                       ||");
        System.out.println(cyan +"  ||                                                                                                                                                               ||");
        System.out.println(cyan +"  ||                                            Answer as many questions as you can correctly before the time runs out!                                            ||");
        System.out.println(cyan +"  ||                                            Earn points, improve your coding knowledge, and track your progress.                                               ||");
        System.out.println(cyan +"  ||                                                                                                                                                               ||");
        System.out.println(cyan +"  ||===============================================================================================================================================================||");
        System.out.println(green +"  ===================================================================================================================================================================");

        mechanics.Mechanics();
        
        String name = input.nextLine();
        Player player = new Player(name);

        GameOfQuiz quiz = new GameOfQuiz(player);
        quiz.start(); 
    }
}
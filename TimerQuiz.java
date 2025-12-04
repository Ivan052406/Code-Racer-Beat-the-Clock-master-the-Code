import java.util.Scanner;

class TimerQuiz {
    private Scanner sc;
    
    public TimerQuiz() {
        this.sc = new Scanner(System.in);
    }
    
    public int chooseDifficulty() {
        String green = "\u001B[32m";
    
        System.out.println("\n=== CODE RACER TIMER ===");
        System.out.println(green +"[1] Easy   (15 seconds)");
        System.out.println(green +"[2] Medium (10 seconds)");
        System.out.println(green +"[3] Hard   (5 seconds)");
        System.out.print("Choose difficulty: ");

        int choice = sc.nextInt();
        sc.nextLine();

        // Return time limit based on user choice using switch expression
        return switch (choice) {
            case 1 -> 15;
            case 2 -> 10;
            case 3 -> 5;
            default -> 10;
        };
    }

    public char startTimer(int seconds) {
    System.out.println("\n Timer: " + seconds + " seconds");
    
    System.out.print("Time left: " + seconds + "s | Your answer (A-D): ");
    System.out.flush();
    
    // Array to track if time has expired
    final boolean[] timeUp = {false};
    
    Thread countdown = new Thread(() -> {
        try {
            for (int i = seconds; i >= 0; i--) {
            
                System.out.print("\rTime left: " + i + "s | Your answer (A-D): ");
                System.out.flush();
                // Pause for 1 second between updates 
                if (i > 0) Thread.sleep(1000);
            }
            timeUp[0] = true;
            System.out.println("\n\n TIME'S UP!");
        } catch (InterruptedException e) {
            
        }
    });
    
    countdown.start();
    
    String input = "";
    try {
    
        input = sc.nextLine().trim().toUpperCase();
        // This Interrupt the countdown thread when the user has responded
        countdown.interrupt(); 
        
        
        if (timeUp[0] && input.isEmpty()) {
            return ' ';
        }
        
        if (input.matches("[A-D]")) {
            return input.charAt(0);
        }
        // This Handle any input exceptions and interrupt the timer
    } catch (Exception e) {
        countdown.interrupt();
    }
    
    return ' ';
}
}
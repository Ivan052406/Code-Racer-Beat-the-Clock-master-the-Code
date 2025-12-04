# Code-Racer-Beat-the-Clock-master-the-Code
A console based quiz game which has timer and no timer different kind of quiz
Code-Racer is a timed, multi-mode quiz game where players answer questions to score points. With a Written (No timer) mode and a Multiple-Choice (With timer) mode where difficulty increases as the timer runs down, the game offers flexible challenge levels. Built using OOP, it's available in Java, C++, and Python. The code uses OOP principles such as abstraction, inheritance, and encapsulation to organize quiz rounds, timer-based answering, and scoring. The system includes: Question class for multiple-choice questions

WrittenQuestion class for typed-answer questions QuizRound abstract class for MCQ quiz categories WrittenQuiz abstract class for written-answer categories PythonWrittenQuiz subclass as an example written quiz Integration with timers and scoring through a Player and TimerQuiz class (not shown in this file)

Features Multiple-Choice Quiz Rounds Randomized question order

Timer-based input using TimerQuiz

Input validation (A–D only)

Tracks and displays round scores

Written-Answer Quizzes Randomized question order

Case-insensitive answer checking

Displays correct answer if wrong

Player Scoring Each quiz adds points to a Player object

Scores accumulate across rounds Class Breakdown

WrittenQuestion Represents a fill-in-the-blank type question. Stores a question and its answer

Normalizes answer comparison using lowercase + trimming

Question Represents an MCQ question with: A question string

An array of options

A correct answer letter (A–D)

QuizRound (Abstract) Defines the structure for MCQ rounds: Loads questions via getQuestions()

Shuffles questions

Uses timer to collect answers

Adds score to the player

WrittenQuiz (Abstract) Defines the structure for written-answer rounds: Randomizes question order

Uses checkAnswer() for validation

Adds score to the player

PythonWrittenQuiz Sample implementation of a WrittenQuiz focused on Python knowledge.

How to Add a New Written Quiz Create a subclass: class JavaWrittenQuiz extends WrittenQuiz { public JavaWrittenQuiz(Scanner sc, Player player) { super(sc, player); }

@Override
protected List<WrittenQuestion> getQuestions() {
    return Arrays.asList(
        new WrittenQuestion("Java keyword for inheritance?", "extends"),
        new WrittenQuestion("File extension for Java?", ".java")
    );
}

@Override
protected String getQuizName() {
    return "Java Written Quiz";
}
} How to Run the Quiz You must: Create a Scanner instance

Create a Player object Create a quiz object (MCQ or Written) Call .startQuiz()

Example: Scanner sc = new Scanner(System.in); Player p = new Player("Carl"); WrittenQuiz quiz = new PythonWrittenQuiz(sc, p); quiz.startQuiz();

Requirements Java 8 or above Terminal/console input support

Notes Ensure TimerQuiz and Player classes are implemented before running.

WrittenQuiz uses nextLine(), so handle newline issues when mixing next() and nextLine().

Author 
-Group algoracers 
-Gutierrez, Carl Ivan P. 
-Ilao Renjay Kengie
-Hermano Rachelle
Julao Jhiro
Author This README was auto‑generated based on your quiz system code structure.

import java.util.Scanner;
import java.util.Random;

class GuessGame {

    int score = 100;
    int attempts = 0;

    String[] history = new String[10];
    int historyCount = 0;

    // Save game history
    void addHistory(String result) {

        if (historyCount < history.length) {

            history[historyCount] = result;
            historyCount++;
        }
    }

    // Start game
    void playGame(Scanner sc) {

        Random random = new Random();

        // Random number from 1 to 100
        int secretNumber = random.nextInt(100) + 1;

        attempts = 0;
        score = 100;

        System.out.println("\n===== NUMBER GUESSING GAME =====");
        System.out.println("Guess a number between 1 and 100.");

        while (true) {

            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();

            attempts++;

            // Correct answer
            if (guess == secretNumber) {

                System.out.println("\nCorrect! You won!");

                System.out.println("Secret Number : " + secretNumber);
                System.out.println("Attempts      : " + attempts);
                System.out.println("Score         : " + score);

                addHistory(
                    "WIN - Attempts: " + attempts
                    + ", Score: " + score
                );

                return;
            }

            // Guess is too high
            else if (guess > secretNumber) {

                System.out.println("Too High!");

            }

            // Guess is too low
            else {

                System.out.println("Too Low!");
            }

            // Reduce score for wrong answer
            score -= 10;

            // Score cannot be negative
            if (score < 0) {

                score = 0;
            }

            System.out.println("Current Score: " + score);
        }
    }

    // Show history
    void showHistory() {

        System.out.println("\n===== GAME HISTORY =====");

        if (historyCount == 0) {

            System.out.println("No game history found.");
            return;
        }

        for (int i = 0; i < historyCount; i++) {

            System.out.println((i + 1) + ". " + history[i]);
        }
    }
}


public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create game object
        GuessGame game = new GuessGame();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("      NUMBER GUESSING GAME");
            System.out.println("==============================");

            System.out.println("1. Play Game");
            System.out.println("2. Game History");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    game.playGame(sc);

                    break;

                case 2:

                    game.showHistory();

                    break;

                case 0:

                    System.out.println(
                        "\nThanks for playing!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice! Try again."
                    );
            }
        }
    }
}
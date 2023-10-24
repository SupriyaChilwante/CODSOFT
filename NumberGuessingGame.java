import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		Random random = new Random();
		int minimumRange = 1;
		int maximumRange = 100;
		int attempt = 0;
		int score = 0;

		boolean playAgain = true;
		while (playAgain) {
			int secretNumber = random.nextInt(maximumRange) + minimumRange;
			System.out.println("*******************************************************************");
			System.out.println("                       Number Guessing Game");
			System.out.println("*******************************************************************");
			System.out.println(" ");
			System.out.println("A number is selected between " + minimumRange + " & " + maximumRange + ". Try to guess it!");

			int userGuess;
			boolean hasGuessedCorrectly = false;
			while (!hasGuessedCorrectly && attempt < 5) {
				System.out.print("Enter your guess: ");
				userGuess = inputScanner.nextInt();
				attempt++;

				if (userGuess == secretNumber) {
					System.out.println("Congratulations! You've guessed it right.");
					hasGuessedCorrectly = true;
					score++;
				} else if (userGuess < secretNumber) {
					System.out.println("Guess is too low. Try again.");
				} else {
					System.out.println("Guess is too high. Try again.");
				}
			}
			System.out.println("You took " + attempt + " attempts to guess the number.");
			System.out.println("Current score : " + score);

			if (attempt >= 5) {
				System.out.println("You have reached the maximum number of attempts.");
			}
			System.out.print("Do you want to play again? (y/n): ");
			String playAgainResponse = inputScanner.next();
			if (!playAgainResponse.equalsIgnoreCase("y")) {
				playAgain = false;
				System.out.println("Thank you for playing.");
			}
			attempt = 0;
		}
		inputScanner.close();
	}
}

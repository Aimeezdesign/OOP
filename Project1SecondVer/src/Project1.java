/**
 * @author Aimee Zhao
 * Creating "Spelling Beehive" game
 * Player is given a set of seven letters and has to find as many words as possible using some portion, but at least five, of those seven letters.
 * Letters may be used more than once. Each correct word earns one point. 
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Project1 extends PuzzleGUI {
	private List<String> foundWords = new ArrayList<>();
	private int score = 0;
	private List<String> solutions;
	private final String validLetters = "latipmo";	//Use String to check 7 letter's validity
	private String enteredLetters = ""; 

	/**
	 * Constructor for project1 class
	 * Accept words from the user via a JOptionPane
	 * Handles error conditions for users' guess
	 * - First check length if it's < 5
	 * - Second check if the letter is one of the valid letters
	 * - Third check if the guess matches the solution list
	 */
	public Project1() {
		super();
		solutions = readSolutions("P1input.txt");
		puzzleLetters.setText(validLetters); // Initialize with valid letters

		while (true) {
			String guess = JOptionPane.showInputDialog(null, "Enter at least 5 letters from these 7 letters 'l, a, t, i, p, m, o' to create a word: ");
			if (guess == null) // If user enters nothing
				break;

			if (guess.length() < 5) {	//First check
				JOptionPane.showMessageDialog(null, "Your guess is less than 5 letters long.");
				continue;
			}

			boolean validGuess = true;
			StringBuilder enteredLettersBuilder = new StringBuilder();
			for (char c : guess.toCharArray()) {
				if (validLetters.indexOf(c) == -1) {	//Second check
					JOptionPane.showMessageDialog(null, "You used a letter that is not one of the valid letters: " + validLetters);
					validGuess = false;
					break;
				} else {
					enteredLettersBuilder.append(c + ","); // Add all entered letters to display
				}
			}

			if (!validGuess) continue;

			enteredLetters = enteredLettersBuilder.toString();
			puzzleLetters.setText(""); // Clear previous letters
			updatePuzzleLetters(enteredLetters); // Display new letters

			if (solutions.contains(guess)) {	//Third check
				if (!foundWords.contains(guess)) {
					foundWords.add(guess);
					score++;
					updateFoundWordsArea(formatWords(foundWords));
					updateScore(score);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Your guess is not in the solutions list.");
			}
		} //while loop ends
	} // project 1() ends

	/**
	 * Reads the solutions from the input file
	 * @param filename The name of the file containing solutions
	 * @return A list of solutions read from the file
	 */
	private List<String> readSolutions(String filename) {
		List<String> solutions = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				solutions.add(scanner.nextLine().trim());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return solutions;
	}

	/**
	 * Formats a list of words into a single string
	 * @param words The list of words to be formatted
	 * @return A formatted string containing the words
	 */
	private String formatWords(List<String> words) {
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Entry for main, easier readability
	 * Used in the main method to start the program
	 * When this line is executed, it creates a new instance of the project1 class, which triggers the constructor project1()
	 * Inside the constructor, the initialization of the game and handling of user input occur
	 */
	
	public static void main(String[] args) {
		new Project1();
	}
	
}//project ends

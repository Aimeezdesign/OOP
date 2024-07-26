import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class represents the main project implementation.
 * It manages the game logic and user interaction.
 */
public class Project2 extends PuzzleGUI {
    private int score = 0;
    private Word[] solutions;
    private UnsortedWordList unsortedWordList;
    private SortedWordList sortedWordList;
    private String enteredLetters = "";
    private char firstSubjectLetter;
    private String validLetters;

    /**
     * Initializes the game and handles user interactions.
     */
    public Project2() {
        solutions = readSolutions("P1input.txt");
        validLetters = solutions[0].getWord().toLowerCase(); // Use the first line of input as valid letters
        firstSubjectLetter = validLetters.charAt(0); // Save the first subject letter
        String puzzleLetters = validLetters; // Initialize with valid letters
        unsortedWordList = new UnsortedWordList();
        sortedWordList = new SortedWordList();

        while (true) {
            String guess = JOptionPane.showInputDialog(null, "Enter at least 5 letters from these " + validLetters.length() + " letters '" + validLetters + "' to create a word: ");
            if (guess == null) // If user enters nothing
                break;

            if (guess.length() < 5) {    // Check length if less than 5
                JOptionPane.showMessageDialog(null, "Your guess is less than 5 letters long.");
                continue;
            }

            boolean validGuess = true;
            StringBuilder enteredLettersBuilder = new StringBuilder();
            for (char c : guess.toCharArray()) {
                if (validLetters.indexOf(Character.toLowerCase(c)) == -1) {    // Check if user used non-given letters
                    JOptionPane.showMessageDialog(null, "You used a letter that is not one of the valid letters: " + validLetters);
                    validGuess = false;
                    break;
                } else {
                    enteredLettersBuilder.append(c).append(","); // Add all entered letters to display
                }
            }

            if (!validGuess) continue;

            enteredLetters = enteredLettersBuilder.toString();
            puzzleLetters = enteredLetters; // Clear previous letters
            updatePuzzleLetters(puzzleLetters); // Display new letters

            if (containsSolution(guess)) {    // Check if letters is on the solution list
                Word newWord = new Word(guess);
                if (!containsWord(sortedWordList, newWord)) {
                    unsortedWordList.add(newWord);
                    sortedWordList.add(newWord);
                    score += calculateScore(guess);
                    updateFoundWordsArea(formatWords(getWords(sortedWordList)));
                    updateScore(score);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your guess is not in the solutions list."); 
            }
        } 
    } 
    
    /**
     * Retrieves the words from a given WordList.
     *
     * @param list The WordList containing the words.
     * @return An array of Word objects.
     */
    private Word[] getWords(WordList list) {
        Word[] words = new Word[list.length];
        WordNode current = list.first.next;
        int i = 0;
        while (current != null) {
            words[i++] = current.data;
            current = current.next;
        }
        return words;
    }

    /**
     * Checks if a given WordList contains a specific word.
     *
     * @param list The WordList to search.
     * @param word The word to check for.
     * @return True if the word is found, otherwise false.
     */
    private boolean containsWord(WordList list, Word word) {
        WordNode current = list.first.next;
        while (current != null) {
            if (current.data.getWord().equalsIgnoreCase(word.getWord())) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Reads the solutions from a file and stores them in an array of Word objects.
     *
     * @param filename The name of the file containing solutions.
     * @return An array of Word objects representing the solutions.
     */
    private Word[] readSolutions(String filename) {
        Word[] solutions = new Word[0];
        try {
            Scanner scanner = new Scanner(new File(filename));
            int count = 0;
            while (scanner.hasNextLine()) {
                solutions = increaseArraySize(solutions, count + 1);
                solutions[count++] = new Word(scanner.nextLine().trim());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return solutions;
    }

    /**
     * Increases the size of an array of Word objects.
     *
     * @param array   The original array.
     * @param newSize The new size of the array.
     * @return The resized array.
     */
    private Word[] increaseArraySize(Word[] array, int newSize) {
        Word[] newArray = new Word[newSize];
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }

    /**
     * Formats an array of Word objects into a string.
     *
     * @param words The array of words to format.
     * @return A string containing the formatted words.
     */
    private String formatWords(Word[] words) {
        StringBuilder sb = new StringBuilder();
        for (Word word : words) {
            sb.append(word.getWord()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Updates the displayed puzzle letters.
     *
     * @param letters The letters to display.
     */
    public void updatePuzzleLetters(String letters) {
        puzzleLetters.setText(letters);
    }

    /**
     * Updates the displayed found words area.
     *
     * @param words The words to display.
     */
    public void updateFoundWordsArea(String words) {
        foundWordsArea.setText(words);
    }

    /**
     * Updates the displayed score.
     *
     * @param score The score to display.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Calculates the score for a guessed word, 3 for containing all 7 letters, one for containing l, 0 for incorrect
     *
     * @param word The guessed word.
     * @return The score for the guessed word.
     */
    private int calculateScore(String word) {
        if (word.length() == validLetters.length()) {
            return 3;
        } else if (word.contains(String.valueOf(firstSubjectLetter))) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checks if the solutions contain a specified word.
     *
     * @param word The word to search for.
     * @return True if the word is found, otherwise false.
     */
    private boolean containsSolution(String word) {
        String lowercaseWord = word.toLowerCase(); // Convert guessed word to lowercase
        for (Word w : solutions) {
            if (w.getWord().equalsIgnoreCase(lowercaseWord)) { // Compare ignoring case
                return true;
            }
        }
        return false;
    }

    /**
     * Main method to start the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Project2();
    }
}

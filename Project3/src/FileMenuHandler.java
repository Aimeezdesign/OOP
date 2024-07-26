import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * The FileMenuHandler class handles file menu actions such as opening a file
 * and processing its contents.
 */
public class FileMenuHandler {
    private Word[] solutions; // Array of valid solutions

    /**
     * Constructs a FileMenuHandler object with the provided array of Word solutions.
     * 
     * @param solutions Array of Word objects representing valid solutions
     */
    public FileMenuHandler(Word[] solutions) {
        this.solutions = solutions;
    }

    /**
     * Handles action events triggered by file menu items.
     * 
     * @param e The ActionEvent to be processed
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                processFile(selectedFile);
            }
        } else if (command.equals("Quit")) {
            System.exit(0);
        }
    }

    /**
     * Processes the contents of the selected file, checking for valid solutions
     * and displaying any illegal words found.
     * 
     * @param file The File object representing the selected file
     */
    private void processFile(File file) {
        StringBuilder foundWords = new StringBuilder();
        StringBuilder illegalWords = new StringBuilder();

        try (FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String trimmedLine = line.trim().toLowerCase();
                    if (containsSolution(trimmedLine)) {
                        foundWords.append(trimmedLine).append("\n");
                    }
                } catch (Project3.IllegalWordException ex) {
                    illegalWords.append("Illegal Word Found: ").append(ex.getIllegalWord()).append("\n");
                }
            }

            if (illegalWords.length() > 0) {
                JOptionPane.showMessageDialog(null, illegalWords.toString(),
                        "Illegal Words", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No illegal words found in the file.",
                        "Illegal Words", JOptionPane.INFORMATION_MESSAGE);
            }

            updateFoundWordsArea(foundWords.toString());
            int score = calculateScore(foundWords.toString());
            updateScore(score);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the provided word exists in the array of solutions.
     * 
     * @param word The word to be checked
     * @return true if the word is a valid solution, otherwise false
     */
    private boolean containsSolution(String word) {
        for (Word w : solutions) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the found words area in the GUI with the provided words.
     * 
     * @param words The words to be displayed in the found words area
     */
    private void updateFoundWordsArea(String words) {
        // Implement this method in PuzzleGUI
    }

    /**
     * Calculates the score based on the found words.
     * 
     * @param foundWords The words found in the file
     * @return The calculated score
     */
    private int calculateScore(String foundWords) {
        // Implement this method in PuzzleGUI
        return 0;
    }

    /**
     * Updates the score in the GUI with the provided score value.
     * 
     * @param score The score to be displayed in the GUI
     */
    private void updateScore(int score) {
        // Implement this method in PuzzleGUI
    }
}

/**
 * @author Aimee Zhao
 * A GUI for the puzzle with a grid layout of one row and two columns.
 * Left column: put the puzzle letters
 * Right column: display the words that the user has found so far and the user’s score
 */
import javax.swing.*;
import java.awt.*;

public class PuzzleGUI {
	protected JTextArea puzzleLetters;
	protected JTextArea foundWordsArea;
	protected JLabel scoreLabel;
	/**
	 * Constructor for PuzzleGUI class.
	 * Sets up the GUI layout and components
	 */
	public PuzzleGUI() {
		JFrame frame = new JFrame("Spelling Beehive Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		JPanel panel = new JPanel(new GridLayout(1, 2));

		/**
		 * Two text area: puzzleLetter & foundWordsArea
		 */
		puzzleLetters = createTextArea();
		JPanel leftPanel = createPanelWithLabelAndTextArea("Letters:", puzzleLetters);
		panel.add(leftPanel);

		foundWordsArea = createTextArea();
		scoreLabel = new JLabel("Score: 0");
		JPanel rightPanel = createPanelWithLabelAndTextArea("Words Found:", foundWordsArea);
		rightPanel.add(scoreLabel, BorderLayout.SOUTH);
		panel.add(rightPanel);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	private JTextArea createTextArea() {
		JTextArea textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		return textArea;
	}

	private JPanel createPanelWithLabelAndTextArea(String labelText, JTextArea textArea) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(labelText);
		panel.add(label, BorderLayout.NORTH);
		panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Updates the puzzle letters displayed on the GUI
	 * @param letters - The letters to be displayed
	 */
	
	public void updatePuzzleLetters(String letters) {
		puzzleLetters.setText(letters);
	}

	/**
	 * Updates the found words area displayed on the GUI
	 * @param words - The words to be displayed
	 */
	public void updateFoundWordsArea(String words) {
		foundWordsArea.setText(words);
	}

	/**
	 * Updates the score displayed on the GUI.
	 * @param score - The score to be displayed
	 */
	
	public void updateScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
}//PuzzleGUI ends

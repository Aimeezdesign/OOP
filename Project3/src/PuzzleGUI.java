import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This class represents the graphical user interface for a spelling beehive puzzle.
 */
public class PuzzleGUI {
    protected JTextArea puzzleLetters;
    protected JTextArea foundWordsArea;
    protected JLabel scoreLabel;

    /**
     * Constructs a PuzzleGUI object and initializes the GUI components.
     */
    public PuzzleGUI() {
        JFrame frame = new JFrame("Spelling Beehive Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(1, 2));

        puzzleLetters = createTextArea();
        JPanel leftPanel = createPanelWithLabelAndTextArea("Letters:", puzzleLetters);
        panel.add(leftPanel);

        foundWordsArea = createTextArea();
        scoreLabel = new JLabel("Score: 0");
        JPanel rightPanel = createPanelWithLabelAndTextArea("Words Found:", foundWordsArea);
        rightPanel.add(scoreLabel, BorderLayout.SOUTH);
        panel.add(rightPanel);

        frame.getContentPane().add(panel);

        // Create the file menu and attach a FileMenuHandler
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem quitMenuItem = new JMenuItem("Quit");

        openMenuItem.addActionListener(new FileMenuHandler());
        quitMenuItem.addActionListener(new FileMenuHandler());

        fileMenu.add(openMenuItem);
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    /**
     * Creates a JTextArea with specified dimensions and properties.
     *
     * @return The created JTextArea.
     */
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        return textArea;
    }

    /**
     * Creates a JPanel with a JLabel and a JTextArea inside it.
     *
     * @param labelText The label text.
     * @param textArea  The JTextArea.
     * @return The created JPanel.
     */
    private JPanel createPanelWithLabelAndTextArea(String labelText, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Updates the puzzle letters JTextArea with the given letters.
     *
     * @param letters The letters to be displayed.
     */
    public void updatePuzzleLetters(String letters) {
        puzzleLetters.setText(letters);
    }

    /**
     * Updates the found words JTextArea with the given words.
     *
     * @param words The words to be displayed.
     */
    public void updateFoundWordsArea(String words) {
        foundWordsArea.setText(words);
    }

    /**
     * Updates the score label with the given score.
     *
     * @param score The score to be displayed.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Handles file menu events.
     */
    private class FileMenuHandler implements ActionListener {
        /**
         * Performs actions based on the file menu event.
         *
         * @param event The ActionEvent.
         */
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if (command.equals("Open")) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Process the selected file (e.g., read and display its content)
                    readFile(selectedFile);
                }
            } else if (command.equals("Quit")) {
                System.exit(0);
            }
        }

        /**
         * Reads the content of the selected file and updates the GUI.
         *
         * @param file The selected file.
         */
        private void readFile(File file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                // Update GUI with file content
                updatePuzzleLetters(content.toString()); // Update puzzleLetters JTextArea
                // Or you can update foundWordsArea JTextArea
                // updateFoundWordsArea(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The main method to launch the application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PuzzleGUI();
            }
        });
    }
}

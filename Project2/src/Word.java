/**
 * This class represents a single word.
 */
public class Word {
    private String word;

    /**
     * Constructs a new Word object with the specified word.
     *
     * @param word The word to be encapsulated.
     */
    public Word(String word) {
        this.word = word;
    }

    /**
     * Retrieves the word.
     *
     * @return The word encapsulated in this object.
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the word.
     *
     * @param word The new word to be encapsulated.
     */
    public void setWord(String word) {
        this.word = word;
    }
}

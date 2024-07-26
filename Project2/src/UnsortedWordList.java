/**
 * This class represents an unsorted linked list of words.
 * It extends the WordList abstract class.
 */
public class UnsortedWordList extends WordList {
    /**
     * Constructs a new UnsortedWordList object with an empty list.
     */
    public UnsortedWordList() {
        super();
    }

    /**
     * Adds a word to the unsorted list by appending it to the end.
     *
     * @param word The word to add.
     */
    public void add(Word word) {
        append(word);
    }
}

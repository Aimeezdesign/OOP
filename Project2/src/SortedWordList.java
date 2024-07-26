/**
 * This class represents a sorted linked list of words.
 * It extends the WordList abstract class.
 */
public class SortedWordList extends WordList {
    /**
     * Constructs a new SortedWordList object with an empty list.
     */
    public SortedWordList() {
        super();
    }

    /**
     * Adds a word to the sorted list in alphabetical order.
     *
     * @param word The word to add.
     */
    public void add(Word word) {
        WordNode newNode = new WordNode(word);
        WordNode current = first.next;
        WordNode previous = first;

        // Going through the list to find the correct position for the new word
        while (current != null && word.getWord().compareToIgnoreCase(current.data.getWord()) > 0) {
            previous = current;
            current = current.next;
        }

        // Insert the new word into the sorted position
        newNode.next = current;
        previous.next = newNode;
        
        // Update the last pointer if the new word is added at the end
        if (current == null) {
            last = newNode;
        }
        
        // Increase the length of the list
        length++;
    }
}

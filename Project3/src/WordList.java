/**
 * This abstract class represents a linked list of words.
 */
public abstract class WordList {
	//instance variables
    protected WordNode first;
    protected WordNode last;
    protected int length;

    /**
     * No-argument constructor new WordList object with an empty list.
     * Create an empty list with first and last pointing to an empty head node, and length equal to zero.
     * Include an append method in this class. 
     */
    public WordList() {
        this.first = new WordNode(null); // Head node
        this.last = this.first;
        this.length = 0;
    }

    /**
     * Abstract method to add a word to the list.
     *
     * @param word The word to add.
     */
    public abstract void add(Word word);

    /**
     * Appends a word to the end of the list.
     *
     * @param word The word to append.
     */
    public void append(Word word) {
        WordNode newNode = new WordNode(word);
        this.last.next = newNode;
        this.last = newNode;
        this.length++;
    }
}

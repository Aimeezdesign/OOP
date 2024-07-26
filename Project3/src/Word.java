class Word {
    private String word;

    public Word(String word) {
        if (!word.matches("[a-z]+")) {
            throw new Project3.IllegalWordException(word);
        }
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}

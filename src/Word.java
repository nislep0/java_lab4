public final class Word implements SentenceElement {
    private final Letter[] letters;

    public Word(Letter[] letters) {
        if (letters == null || letters.length == 0) {
            throw new IllegalArgumentException("Word letters are empty.");
        }
        this.letters = letters;
    }

    public Letter[] getLetters() {
        return letters.clone();
    }

    @Override
    public String toPlainString() {
        StringBuilder sb = new StringBuilder(letters.length);
        for (Letter l : letters) {
            sb.append(l.getValue());
        }
        return sb.toString();
    }
}
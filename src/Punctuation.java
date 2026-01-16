public final class Punctuation implements SentenceElement {
    private final char mark;

    public Punctuation(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    @Override
    public String toPlainString() {
        return String.valueOf(mark);
    }
}
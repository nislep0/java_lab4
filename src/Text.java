public final class Text {
    private final Sentence[] sentences;

    public Text(Sentence[] sentences) {
        if (sentences == null || sentences.length == 0) {
            throw new IllegalArgumentException("Text has no sentences.");
        }
        this.sentences = sentences;
    }

    public Sentence[] getSentences() {
        return sentences.clone();
    }

    public String toPlainString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(sentences[i].toPlainString());
        }
        return sb.toString();
    }
}
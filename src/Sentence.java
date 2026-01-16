import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

public final class Sentence {
    private final SentenceElement[] elements;

    public Sentence(SentenceElement[] elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("Sentence elements are empty.");
        }
        this.elements = elements;
    }

    public SentenceElement[] getElements() {
        return elements.clone();
    }

    public List<Word> getWordsInOrder() {
        List<Word> words = new ArrayList<>();
        for (SentenceElement e : elements) {
            if (e instanceof Word) {
                words.add((Word) e);
            }
        }
        return words;
    }

    public LinkedHashSet<String> getWordsAsLowercaseSet() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (Word w : getWordsInOrder()) {
            set.add(w.toPlainString().toLowerCase(Locale.ROOT));
        }
        return set;
    }

    public String toPlainString() {
        StringBuilder sb = new StringBuilder();
        boolean lastWasWord = false;

        for (SentenceElement e : elements) {
            if (e instanceof Word) {
                if (!sb.isEmpty()) {
                    sb.append(' ');
                }
                sb.append(e.toPlainString());
                lastWasWord = true;
            } else {
                // punctuation
                if (!lastWasWord && !sb.isEmpty()) {
                    // If punctuation appears without preceding word, keep it attached anyway.
                }
                sb.append(e.toPlainString());
                lastWasWord = false;
            }
        }
        return sb.toString();
    }
}
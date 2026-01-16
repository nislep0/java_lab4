import java.util.ArrayList;
import java.util.List;

public final class TextParser {

    private TextParser() {}

    public static Text parse(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null.");
        }
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Text is empty.");
        }

        List<Sentence> sentences = new ArrayList<>();
        List<SentenceElement> currentElements = new ArrayList<>();

        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < trimmed.length(); i++) {
            char ch = trimmed.charAt(i);

            if (ch == ' ') {
                flushWordIfAny(currentWord, currentElements);
                continue;
            }

            if (isWordChar(ch)) {
                currentWord.append(ch);
                continue;
            }

            flushWordIfAny(currentWord, currentElements);
            currentElements.add(new Punctuation(ch));

            if (isSentenceTerminator(ch)) {
                flushSentenceIfAny(currentElements, sentences);
            }
        }

        flushWordIfAny(currentWord, currentElements);
        flushSentenceIfAny(currentElements, sentences);

        if (sentences.isEmpty()) {
            throw new IllegalArgumentException("No sentences could be parsed.");
        }

        return new Text(sentences.toArray(new Sentence[0]));
    }

    private static boolean isWordChar(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '\'';
    }

    private static boolean isSentenceTerminator(char ch) {
        return ch == '.' || ch == '!' || ch == '?';
    }

    private static void flushWordIfAny(StringBuilder word, List<SentenceElement> elements) {
        if (word.isEmpty()) {
            return;
        }
        Letter[] letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
        elements.add(new Word(letters));
        word.setLength(0);
    }

    private static void flushSentenceIfAny(List<SentenceElement> elements, List<Sentence> sentences) {
        if (elements.isEmpty()) {
            return;
        }
        SentenceElement[] arr = elements.toArray(new SentenceElement[0]);
        sentences.add(new Sentence(arr));
        elements.clear();
    }
}
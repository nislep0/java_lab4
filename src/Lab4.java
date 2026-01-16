import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Lab4 {

    static void main() {
        try {
            String rawText = "Lorem ipsum felis sit amet, placerat adipiscing leo. "
                    + "Duis posuere lacinia arcu, vitae convallis eros tincidunt a. "
                    + "Vestibulum in lacinia metus. "
                    + "Integer eleifend enim eget mollis posuere. Aenean vitae ligula ac quam molestie semper viverra id odio. "
                    + "In orci diam, convallis vitae egestas ut, mattis eget felis. "
                    + "Mauris luctus, eros vel ultricies ornare, urna leo consequat ligula, sit amet placerat nibh arcu id felis. "
                    + "Sed ac metus non enim tincidunt blandit.";

            String normalized = normalizeWhitespace(rawText);
            Text text = TextParser.parse(normalized);

            System.out.println("Normalized text:");
            System.out.println(text.toPlainString());

            Word[] uniqueWords = findUniqueWordsFromFirstSentence(text);

            System.out.println("\nUnique words from the first sentence (absent in all following sentences):");
            if (uniqueWords.length == 0) {
                System.out.println("(none)");
            } else {
                for (Word w : uniqueWords) {
                    System.out.println(w.toPlainString());
                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String normalizeWhitespace(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null.");
        }
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Text is empty.");
        }
        return trimmed.replaceAll("\\s+", " ");
    }

    private static Word[] findUniqueWordsFromFirstSentence(Text text) {
        if (text == null) {
            throw new IllegalArgumentException("Text object is null.");
        }
        Sentence[] sentences = text.getSentences();
        if (sentences.length == 0) {
            throw new IllegalArgumentException("Text has no sentences.");
        }

        Sentence first = sentences[0];
        LinkedHashSet<String> firstWords = first.getWordsAsLowercaseSet();
        if (firstWords.isEmpty()) {
            throw new IllegalArgumentException("First sentence has no words.");
        }

        HashSet<String> otherWords = new HashSet<>();
        for (int i = 1; i < sentences.length; i++) {
            otherWords.addAll(sentences[i].getWordsAsLowercaseSet());
        }

        List<Word> result = new ArrayList<>();
        for (Word w : first.getWordsInOrder()) {
            String key = w.toPlainString().toLowerCase(Locale.ROOT);
            if (!otherWords.contains(key)) {
                result.add(w);
            }
        }

        return result.toArray(new Word[0]);
    }
}

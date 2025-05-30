package ed.lab.ed1final.trie;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int prefixCount = 0;
        int wordCount = 0;
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) return;

        word = word.trim().toLowerCase();  // Normalización
        TrieNode current = root;
        current.prefixCount++;

        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
            current.prefixCount++;
        }
        current.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        if (word == null || word.isEmpty()) return 0;

        word = word.trim().toLowerCase();
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) return 0;
        }
        return current.wordCount;
    }


    public int countWordsStartingWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) return 0;

        prefix = prefix.trim().toLowerCase();
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null) return 0;
        }
        return current.prefixCount;
    }

    public void erase(String word) {
        if (word == null || word.isEmpty() || countWordsEqualTo(word) == 0) return;

        word = word.trim().toLowerCase();
        TrieNode current = root;
        current.prefixCount--;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode child = current.children.get(c);

            if (child.prefixCount == 1) {
                current.children.remove(c);
                return;
            }

            child.prefixCount--;
            current = child;
        }

        current.wordCount--;
    }

    public boolean isEmpty() {
        return root.prefixCount == 0;
    }

    public void clear() {
        root.children.clear();
        root.prefixCount = 0;
        root.wordCount = 0;
    }
}



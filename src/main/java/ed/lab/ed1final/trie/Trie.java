package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Component
public class Trie {

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int wordCount = 0;
        int prefixCount = 0;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.prefixCount++;
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.prefixCount++;
        node.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return 0;
            }
            node = node.children.get(ch);
        }
        return node.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return 0;
            }
            node = node.children.get(ch);
        }
        return node.prefixCount;
    }

    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) return; // No hay nada que borrar

        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.prefixCount--;
            node = node.children.get(ch);
        }
        node.prefixCount--;
        node.wordCount--;
    }
}







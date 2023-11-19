package com.tads;

import java.util.ArrayList;
import java.util.List;
public class BST {

    Node root;

    public void insert(String palavra) {
        root = insert(root, palavra);
    }

    // Função para inserir uma nova palavra na árvore
    private Node insert(Node root, String palavra) {
        if (root == null) {
            return new Node(palavra);
        }

        int comparacao = palavra.compareTo(root.word);
        if (comparacao < 0) {
            root.left = insert(root.left, palavra);
        } else if (comparacao > 0) {
            root.right = insert(root.right, palavra);
        } else {
            root.frequency++;
        }

        return root;
    }

    public List<Node> getInOrderNodes() {
        List<Node> result = new ArrayList<>();
        traverseInOrder(root, result);
        return result;
    }

    // Função de travessia in-order modificada para obter todos os nós
    private void traverseInOrder(Node root, List<Node> result) {
        if (root != null) {
            traverseInOrder(root.right, result);
            result.add(root);
            traverseInOrder(root.left, result);
        }
    }

    public Node search(String palavra) {
        return search(root, palavra);
    }

    // Função de busca recursiva
    private Node search(Node root, String palavra) {
        if (root == null || palavra.equals(root.word)) {
            return root;
        }

        int comparacao = palavra.compareTo(root.word);
        if (comparacao < 0) {
            return search(root.left, palavra);
        } else {
            return search(root.right, palavra);
        }
    }

    class Node {
        String word;
        int frequency;
        Node left, right;

        public Node(String word) {
            this.word = word;
            this.frequency = 1;
            this.left = this.right = null;
        }
    }

}
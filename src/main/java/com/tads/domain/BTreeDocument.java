package com.tads.domain;

import java.util.ArrayList;
import java.util.List;

public class BTreeDocument {

    private Node root;
    private String pathDocument;
    private Integer totalElements = 0;
    private double termFrequencyInverseDocumentFrequency;

    public void insert(String palavra) {
        root = insert(root, palavra);
    }

    public Node search(String palavra) {
        return search(root, palavra);
    }

    public List<Node> getInOrderNodes() {
        List<Node> result = new ArrayList<>();
        traverseInOrder(root, result);
        return result;
    }

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

        this.totalElements++;
        return root;
    }

    private void traverseInOrder(Node root, List<Node> result) {
        if (root != null) {
            traverseInOrder(root.right, result);
            result.add(root);
            traverseInOrder(root.left, result);
        }
    }

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

    public class Node {
        public String word;
        public int frequency;
        Node left, right;

        public Node(String word) {
            this.word = word;
            this.frequency = 1;
            this.left = this.right = null;
        }

    }

    public Node getRoot() {
        return root;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public String getPathDocument() {
        return pathDocument;
    }

    public void setPathDocument(String pathDocument) {
        this.pathDocument = pathDocument;
    }

    public void setTermFrequencyInverseDocumentFrequency(double termFrequencyInverseDocumentFrequency) {
        this.termFrequencyInverseDocumentFrequency = termFrequencyInverseDocumentFrequency;
    }

    public double getTermFrequencyInverseDocumentFrequency() {
        return termFrequencyInverseDocumentFrequency;
    }
}
package com.tads;

import com.tads.cli.ReaderCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        String[] simulatedArgs = {
//                "--freq",
//                "5",
//                "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento.txt"
//        };

        Path filePath;
        Integer numberOfWord;

        try{
//            ReaderCLI readerCLI = new ReaderCLI(simulatedArgs);
            ReaderCLI readerCLI = new ReaderCLI(args);

            filePath = readerCLI.getFilePath();
            numberOfWord = readerCLI.getNumberOfWord();

            BST bst = new BST();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        if (!word.isEmpty() && word.length() > 2) {
                            bst.insert(word);
                        }
                    }
                }

                // Obter a lista de nós ordenados por frequência
                List<BST.Node> nodesInOrder = bst.getInOrderNodes();

                nodesInOrder.sort(Comparator.comparingInt(node -> -node.frequency));

                // Imprimir os N nós com as palavras mais frequentes
                for (int i = 0; i < Math.min(numberOfWord, nodesInOrder.size()); i++) {
                    BST.Node node = nodesInOrder.get(i);
                    System.out.println(node.word + ": " + node.frequency);
                }

//                BST.Node search = bst.search(wordSearch);
//                System.out.println(search.word + " - " + search.frequency);


                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
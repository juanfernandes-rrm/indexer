package com.tads.utils;

import com.tads.domain.BTreeDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class ReaderFile {

    public static void readFiles(List<BTreeDocument> BTreeDocuments, List<File> files) {
        for(File file : files){
            BTreeDocument BTreeDocument = new BTreeDocument();
            BTreeDocument.setPathDocument(file.getPath());
            readFile(BTreeDocument, file);
            BTreeDocuments.add(BTreeDocument);
        }
    }

    public static void readFile(BTreeDocument BTreeDocument, File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (word.length() > 2) {
                        BTreeDocument.insert(word);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

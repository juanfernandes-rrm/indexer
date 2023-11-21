package com.tads.utils;

import com.tads.domain.BTreeDocument;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public abstract class ReaderFile {

    public static void readFiles(List<BTreeDocument> BTreeDocuments, List<File> files) {
        files.stream().forEach(file -> {
            BTreeDocument BTreeDocument = new BTreeDocument();
            BTreeDocument.setPathDocument(file.getPath());
            readFile(BTreeDocument, file);
            BTreeDocuments.add(BTreeDocument);
        });
    }

    public static void readFile(BTreeDocument bTreeDocument, File file){
        try {
            Files.lines(file.toPath(),StandardCharsets.ISO_8859_1)
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(word -> word.length() > 2)
                    .forEach(bTreeDocument::insert);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

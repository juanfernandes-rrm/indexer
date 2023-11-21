package com.tads.options.arguments;

import java.nio.file.Path;

public class SearchWordFrequencyArgument extends IndexerArgument{

    private String word;
    private Path filePath;

    public SearchWordFrequencyArgument(String searchWord, Path filePath) {
        this.word = searchWord;
        this.filePath = filePath;
    }

    public String getWord() {
        return word;
    }

    public Path getFilePath() {
        return filePath;
    }
}

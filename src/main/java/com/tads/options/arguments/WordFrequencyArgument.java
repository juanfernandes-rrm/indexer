package com.tads.options.arguments;

import java.nio.file.Path;

public class WordFrequencyArgument extends IndexerArgument {

    private Integer numberOfWord;
    private Path filePath;

    public WordFrequencyArgument(Integer numberOfWord, Path filePath) {
        this.numberOfWord = numberOfWord;
        this.filePath = filePath;
    }

    public Integer getNumberOfWord() {
        return numberOfWord;
    }

    public Path getFilePath() {
        return filePath;
    }
}

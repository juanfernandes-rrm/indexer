package com.tads.options.arguments;

import java.io.File;
import java.util.List;

public class MostRelevantFilesArgument extends IndexerArgument{

    private String term;
    private List<File> files;

    public MostRelevantFilesArgument(String term, List<File> files) {
        this.term = term;
        this.files = files;
    }

    public String getTerm() {
        return term;
    }

    public List<File> getFiles() {
        return files;
    }
}

package com.tads.options;

import com.tads.domain.BTreeDocument;
import com.tads.options.arguments.SearchWordFrequencyArgument;
import com.tads.utils.ReaderFile;

import java.util.Optional;

public class SearchWordFrequencyOption implements IndexerOption{

    private SearchWordFrequencyArgument argument;

    public SearchWordFrequencyOption(SearchWordFrequencyArgument argument) {
        this.argument = argument;
    }

    @Override
    public void execute() {
        BTreeDocument bTreeDocument = new BTreeDocument();
        ReaderFile.readFile(bTreeDocument, argument.getFilePath().toFile());
        searchFrequencyOfWordAndPrint(bTreeDocument, argument.getWord());
    }

    private void searchFrequencyOfWordAndPrint(BTreeDocument BTreeDocument, String wordSearch){
        BTreeDocument.Node search = BTreeDocument.search(wordSearch);
        int frequencyOfWord = searchFrequencyOfWord(BTreeDocument, wordSearch);
        if(frequencyOfWord == 0){
            System.out.println("Palavra não encontrada");
        }else{
            System.out.println(search.word + " - " + search.frequency);
        }
    }

    private static int searchFrequencyOfWord(BTreeDocument BTreeDocument, String wordSearch){
        return Optional.ofNullable(BTreeDocument.search(wordSearch))
                .map(node -> node.frequency)
                .orElse(0);
    }
}

package com.tads.options;

import com.tads.utils.ReaderFile;
import com.tads.domain.BTreeDocument;
import com.tads.options.arguments.WordFrequencyArgument;

import java.util.Comparator;
import java.util.List;

public class WordFrequencyOption implements IndexerOption{

    private WordFrequencyArgument argument;

    public WordFrequencyOption(WordFrequencyArgument argument) {
        this.argument = argument;
    }

    @Override
    public void execute() {
        BTreeDocument bTreeDocument = new BTreeDocument();
        ReaderFile.readFile(bTreeDocument, argument.getFilePath().toFile());
        frequencyOfWord(bTreeDocument, argument.getNumberOfWord());
    }

    private void frequencyOfWord(BTreeDocument BTreeDocument, Integer numberOfWord){
        List<BTreeDocument.Node> nodesInOrder = BTreeDocument.getInOrderNodes();

        nodesInOrder.sort(Comparator.comparingInt(node -> -node.frequency));

        for (int i = 0; i < Math.min(numberOfWord, nodesInOrder.size()); i++) {
            BTreeDocument.Node node = nodesInOrder.get(i);
            System.out.println(node.word + ": " + node.frequency);
        }
    }
}

package com.tads.options;

import com.tads.utils.ReaderFile;
import com.tads.utils.CalculatorTermFrequencyInverseDocumentFrequency;
import com.tads.domain.BTreeDocument;
import com.tads.options.arguments.MostRelevantFilesArgument;

import java.util.ArrayList;
import java.util.List;

public class MostRelevantFilesOption implements IndexerOption {

    private MostRelevantFilesArgument argument;

    public MostRelevantFilesOption(MostRelevantFilesArgument mostRelevantFilesArgument) {
        this.argument = mostRelevantFilesArgument;
    }

    @Override
    public void execute() {
        List<BTreeDocument> btreeDocuments = new ArrayList<>();
        ReaderFile.readFiles(btreeDocuments, argument.getFiles());
        CalculatorTermFrequencyInverseDocumentFrequency.calculate(argument.getTerm(), btreeDocuments);
        CalculatorTermFrequencyInverseDocumentFrequency.showTermFrequencyInverseDocumentFrequency(btreeDocuments);
    }
}

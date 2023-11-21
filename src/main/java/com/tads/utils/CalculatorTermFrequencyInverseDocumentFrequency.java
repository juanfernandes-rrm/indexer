package com.tads.utils;

import com.tads.domain.BTreeDocument;

import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public abstract class CalculatorTermFrequencyInverseDocumentFrequency {


    public static void calculate(String term, List<BTreeDocument> BTreeDocuments){
        String[] words = term.split(" ");

        for(BTreeDocument BTreeDocument : BTreeDocuments){
            double TFIDF = 0;
            for(String word : words){
                TFIDF += calculateTermFrequency(word, BTreeDocument) * calculateInverseDocumentFrequency(BTreeDocuments.size(), calculateFilesWithTerm(word, BTreeDocuments));
            }
            double averageTFIDF = TFIDF / words.length;
            BTreeDocument.setTermFrequencyInverseDocumentFrequency(averageTFIDF);
        }
    }

    public static void showTermFrequencyInverseDocumentFrequency(List<BTreeDocument> BTreeDocuments){
        BTreeDocuments.stream()
                .sorted(Comparator.comparingDouble(BTreeDocument::getTermFrequencyInverseDocumentFrequency).reversed())
                .forEach(BTreeDocument -> System.out.println(BTreeDocument.getTermFrequencyInverseDocumentFrequency() + " - " + BTreeDocument.getPathDocument()));
    }

    private static double calculateTermFrequency(String term, BTreeDocument BTreeDocument){
        int frequencyOfWord = searchFrequencyOfWord(BTreeDocument, term);
        if(frequencyOfWord > 0){
            return frequencyOfWord / BTreeDocument.getTotalElements().doubleValue();
        }
        return 0;
    }

    private static double calculateInverseDocumentFrequency(Integer numberFile, Integer numberFileWithTerm){
        if(numberFile != 0 && numberFileWithTerm != 0){
            return Math.log10(numberFile/numberFileWithTerm);
        }
        return 0;
    }

    private static Integer calculateFilesWithTerm(String term, List<BTreeDocument> BTreeDocuments){
        int quantityFileWithTerm = 0;
        for(BTreeDocument BTreeDocument : BTreeDocuments){
            if(searchFrequencyOfWord(BTreeDocument, term) > 0) {
                quantityFileWithTerm++;
            }
        }
        return quantityFileWithTerm;
    }

    private static int searchFrequencyOfWord(BTreeDocument BTreeDocument, String wordSearch){
        BTreeDocument.Node search = BTreeDocument.search(wordSearch);
        return isNull(search) ? 0 : search.frequency;
    }

}

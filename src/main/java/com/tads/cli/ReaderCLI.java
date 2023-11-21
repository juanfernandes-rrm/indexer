package com.tads.cli;

import com.tads.options.SearchWordFrequencyOption;
import com.tads.options.IndexerOption;
import com.tads.options.MostRelevantFilesOption;
import com.tads.options.WordFrequencyOption;
import com.tads.options.arguments.SearchWordFrequencyArgument;
import com.tads.options.arguments.MostRelevantFilesArgument;
import com.tads.options.arguments.WordFrequencyArgument;
import org.apache.commons.cli.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReaderCLI {

    private IndexerOption indexerOption;

    public ReaderCLI(String[] args) {
        Options options = createOptions();
        parseArguments(args, options);
    }

    private Options createOptions() {
        var options = new Options();

        var wordFrequencyOption = new Option("f", "freq", true,
                "Exibe o número de ocorrência das N palavras que mais aparecem em ARQUIVO, em ordem decrescente de ocorrência.\n");
        options.addOption(wordFrequencyOption);

        var searchWordFrequencyOption = new Option("fw", "freq-word", true,
                "Exibe o número de ocorrências de PALAVRA em ARQUIVO. ");
        options.addOption(searchWordFrequencyOption);

        var searchTermFrequencyOption = new Option("s", "search", true,
                "Exibe uma listagem dos ARQUIVOS mais relevantes encontrados pela busca por \n" +
                        "    TERMO. A listagem é apresentada em ordem descrescente de relevância. \n" +
                        "    TERMO pode conter mais de uma palavra. Neste caso, deve ser indicado entre \n" +
                        "    àspas.\n");
        options.addOption(searchTermFrequencyOption);

        return options;
    }

    private void parseArguments(String[] args, Options options) {
        CommandLineParser cmdParser = new DefaultParser();
        var ajuda = new HelpFormatter();

        try {
            CommandLine cmd = cmdParser.parse(options, args);

            if(cmd.hasOption("f")){
                var wordNumber = treatFrequencyWordOption(cmd);
                var filePath = treatFileArgument(cmd);
                WordFrequencyArgument argument = new WordFrequencyArgument(wordNumber, filePath);
                this.indexerOption = new WordFrequencyOption(argument);
            }

            if(cmd.hasOption("fw")){
                var searchWord = treatSearchWordArgument(cmd);
                var filePath = treatFileArgument(cmd);
                SearchWordFrequencyArgument argument = new SearchWordFrequencyArgument(searchWord, filePath);
                this.indexerOption = new SearchWordFrequencyOption(argument);
            }

            if(cmd.hasOption("s")) {
                var term = treatTermFrequencyOption(cmd);
                var files = treatFilesArguments(cmd);
                MostRelevantFilesArgument mostRelevantFilesArgument = new MostRelevantFilesArgument(term, files);
                this.indexerOption = new MostRelevantFilesOption(mostRelevantFilesArgument);
            }

        } catch (ParseException e) {
            ajuda.printHelp("indexer", options);
            throw new IllegalArgumentException("Opções inválidas", e);
        }
    }

    private String treatTermFrequencyOption(CommandLine cmd) {
        String searchTerm = cmd.getOptionValue("search");
        searchTerm = searchTerm.replaceAll("'", "").toLowerCase();
        if(!(searchTerm.length() > 2)) {
            throw new IllegalArgumentException("É necessário fornecer uma palavra para pesquisar.");
        }
        return searchTerm;
    }

    private Integer treatFrequencyWordOption(CommandLine cmd) {
        Optional<Integer> numberOfWords = treatNumberArgument(cmd);
        if (numberOfWords.isPresent()) {
            return numberOfWords.get();
        } else {
            throw new IllegalArgumentException("Número inválido.");
        }
    }

    private String treatSearchWordArgument(CommandLine cmd) {
        String searchWord = cmd.getOptionValue("freq-word");
        searchWord = searchWord.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if(!(searchWord.length() > 2)) {
            throw new IllegalArgumentException("É necessário fornecer uma palavra para pesquisar.");
        }
        return searchWord;
    }

    private Optional<Integer> treatNumberArgument(CommandLine cmd) {
        String freq = cmd.getOptionValue("freq");
        try {
            int numberOfWords = Integer.parseInt(freq);
            return numberOfWords > 0 ? Optional.of(numberOfWords) : Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private List<File> treatFilesArguments(CommandLine cmd) {
        String[] positionalArgs = cmd.getArgs();
        if (positionalArgs.length == 0) {
            throw new IllegalArgumentException("É necessário fornecer o caminho para o arquivo.");
        }

        List<File> files = new ArrayList<>();

        for(String args : positionalArgs){
            Path path = Path.of(args);
            if (!Files.exists(path)) {
                throw new IllegalArgumentException(path + " não é um arquivo.");
            }
            files.add(new File(args));
        }
        return files;
    }

    private Path treatFileArgument(CommandLine cmd){
        String[] positionalArgs = cmd.getArgs();
        if (positionalArgs.length == 0) {
            throw new IllegalArgumentException("É necessário fornecer o caminho para o arquivo.");
        }

        String filePath = positionalArgs[0];
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(filePath + " não é um arquivo.");
        }
        return path;
    }

    public IndexerOption getIndexerOption() {
        return indexerOption;
    }
}

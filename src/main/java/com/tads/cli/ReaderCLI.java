package com.tads.cli;

import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ReaderCLI {

    private Integer numberOfWord;
    private Path filePath;

    public ReaderCLI(String[] args) {
        Options options = createOptions();
        CommandLine cmd = parseArguments(args, options);
        treatFrequencyWordOption(cmd);
    }

    private Options createOptions() {
        var options = new Options();

        var wordFrequencyOption = new Option("f", "freq", true,
                "Retorna as N palavras mais frequentes no arquivo fornecido.");
        options.addOption(wordFrequencyOption);

        return options;
    }

    private CommandLine parseArguments(String[] args, Options options) {
        CommandLineParser cmdParser = new DefaultParser();
        var ajuda = new HelpFormatter();

        try {
            return cmdParser.parse(options, args);
        } catch (ParseException e) {
            ajuda.printHelp("indexer", options);
            throw new IllegalArgumentException("Opções inválidas", e);
        }
    }

    private void treatFrequencyWordOption(CommandLine cmd) {
        Optional<Integer> numberOfWords = treatNumberOption(cmd);
        if (numberOfWords.isPresent()) {
            numberOfWord = numberOfWords.get();
        } else {
            throw new IllegalArgumentException("Número inválido.");
        }

        filePath = treatsFileOption(cmd);
    }

    private Optional<Integer> treatNumberOption(CommandLine cmd) {
        String freq = cmd.getOptionValue("freq");
        try {
            int numberOfWords = Integer.parseInt(freq);
            return numberOfWords > 0 ? Optional.of(numberOfWords) : Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Path treatsFileOption(CommandLine cmd){
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

    public Integer getNumberOfWord() {
        return numberOfWord;
    }

    public Path getFilePath() {
        return filePath;
    }
}

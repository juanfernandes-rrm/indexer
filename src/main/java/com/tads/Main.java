package com.tads;

import com.tads.cli.ReaderCLI;
import com.tads.options.IndexerOption;

public class Main {
    public static void main(String[] args) {
//        String[] simulatedArgs = {
//            "--search",
//            "'Lorem Ipsum is simply'",
//            "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento.txt",
//                "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento2.txt",
//                "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento3.txt"
//        };

//        String[] simulatedArgs = {
//                "--freq-word",
//                "Lorem",
//                "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento.txt",
//        };

//        String[] simulatedArgs = {
//                "--freq",
//                "5",
//                "C:\\Users\\PC\\Documents\\tads\\indexer\\src\\main\\java\\com\\tads\\documento.txt",
//        };

        try{
//            ReaderCLI readerCLI = new ReaderCLI(simulatedArgs);
            ReaderCLI readerCLI = new ReaderCLI(args);

            IndexerOption indexerOption = readerCLI.getIndexerOption();
            indexerOption.execute();

        }catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
package com.tads;

import com.tads.cli.ReaderCLI;
import com.tads.options.IndexerOption;

public class Main {
    public static void main(String[] args) {

                String[] simulatedArgs = {
                "--freq",
                "5",
                "C:\\Users\\PC\\Desktop\\1gb.txt\\1gb.txt",
        };
        long startTime = System.currentTimeMillis();

        try{
            ReaderCLI readerCLI = new ReaderCLI(simulatedArgs);

            IndexerOption indexerOption = readerCLI.getIndexerOption();
            indexerOption.execute();

        }catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long minutes = (executionTime / 1000) / 60;
        long seconds = (executionTime / 1000) % 60;
        System.out.println("Tempo total de execução: " + minutes + " minutos e " + seconds + " segundos");
    }
}
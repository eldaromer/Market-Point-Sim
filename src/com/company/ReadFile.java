package com.company;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Created by Omer on 10/8/2015.
 */
public class ReadFile {
    private File toRead;

    public ReadFile(File read) {
        toRead = read;
    }

    public String [] OpenFile () throws IOException{
        FileReader fr = new FileReader(toRead);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = readLines();
        String [] textData = new String[numberOfLines];

        for (int i = 0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }

        textReader.close();
        return textData;
    }

    int readLines() throws IOException {
        FileReader file_to_read = new FileReader(toRead);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        int numberOfLines = 0;

        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }

        bf.close();

        return numberOfLines;
    }
}

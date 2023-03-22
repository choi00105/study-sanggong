package com.test.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class fileManger {
    public static void main(String[] args) throws IOException {
        String path = "c:\\data\\member.txt";
        BufferedReader reader
        = new BufferedReader(new FileReader(path, Charset.forName("UTF-8")));

        String str;
        String[] lineArray;

        while((str = reader.readLine())!=null) { // EOF(End Of File) 만날때 까지
            lineArray = str.split(",");
            System.out.println(lineArray[0] + "\t" + lineArray[1] + "\t" + lineArray[2]);

        }
        reader.close();
        
    }
}

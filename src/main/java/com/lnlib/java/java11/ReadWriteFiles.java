package com.lnlib.java.java11;

import java.io.IOException;
import java.nio.file.Files;

public class ReadWriteFiles
{
    public static void main(String[] args) throws IOException
    {
        var path = Files.writeString(Files.createTempFile("test", ".txt"), "A simple text\nwith new lines");
        System.out.println(path);

        var string = Files.readString(path);
        System.out.println(string);
    }
}

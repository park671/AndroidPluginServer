package com.park.metalmax;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static int last_lev = 0;
    private static StringBuilder stringBuilder = new StringBuilder();
    static BufferedWriter writer;

    private static void print(String msg, int level) {
        if (level <= last_lev) {
            stringBuilder.append("\n");
            last_lev = level;
        }
        for (int i = 0; i < level; i++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append(msg);
    }

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        File file = new File("./usage.csv");
        FileOutputStream outputStream = new FileOutputStream(file);
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line != null && !line.equals("")) {
                if (line.contains("ks-features")) {
                    print(line, 0);
                } else if (line.contains("com.")) {
                    print(line, 1);
                } else if (!line.contains(".")) {
                    print(line, 2);
                } else if (line.contains("import")) {
                    print(line, 3);
                } else {
                    print(line, 4);
                }
            }
        }
        System.out.println(stringBuilder.toString());
        writer.write(stringBuilder.toString());
        writer.flush();
        writer.close();
        outputStream.flush();
        outputStream.close();
    }
}

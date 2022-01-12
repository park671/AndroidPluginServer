package com.park.metalmax;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestServer {

    public static void send(InputStream fileInputStream) throws Throwable{
        ServerSocket serverSocket = new ServerSocket(6710);
        while(true){
            System.out.print("wait for link...");
            Socket socket = serverSocket.accept();
            System.out.print("linked.");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len = fileInputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
            System.out.print("over.");
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            fileInputStream.close();
        }
    }

    public static void main(String[] args) throws Throwable{
        System.out.print("input file path:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String path = scanner.nextLine();
            FileInputStream inputStream = new FileInputStream(path);
            send(inputStream);
        }

    }

}

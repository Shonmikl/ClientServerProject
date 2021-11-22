package socketor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone {
    private ServerSocket server;
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Phone(Phone phoneServer) {
        this.server = phoneServer.server;
        accept();
    }

    public Phone(String port) {
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Phone(String ip, String port) {
        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept() {
        try {
            client = server.accept();
            createStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStreams() {
        try {
            reader = new BufferedReader
                        (new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter
                        (new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
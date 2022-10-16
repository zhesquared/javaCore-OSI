package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 10001;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Eugene");

            String resp = in.readLine();
            System.out.println(resp);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 10001;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                String answer = in.readLine();
                System.out.println(answer);


                String message = scanner.nextLine(); //бесконечно ждет ввода 1 - вопрос, 1 ответ
                out.println(message);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

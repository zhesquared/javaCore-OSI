package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 10001;

        try (ServerSocket serverSocket = new ServerSocket(port)) { //создание серверного подключения
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); //ожидание клиента
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted");

                    greetings(clientSocket, out, in);

                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

    public static void greetings(Socket clientSocket, PrintWriter out, BufferedReader in) throws IOException {
        out.println("Hello, user, what is your name?");
        String name = in.readLine();
        System.out.println("from client " + clientSocket.getLocalAddress() + ": " + name);
        out.println("Nice to meet you, " + name + "! You are robot? \"y/n\" ");
        String answer = in.readLine();
        System.out.println("from client " + clientSocket.getLocalAddress() + ": " + answer);

        if (answer.equalsIgnoreCase("n")) {
            captcha(clientSocket, out, in);
        } else {
            out.println("Sorry, I can't give you access. Can you try again? Say something");
            System.out.println("Access denied " + clientSocket.getLocalAddress());
            String message = in.readLine();
            System.out.println("from client " + clientSocket.getLocalAddress() + ": " + message);
            greetings(clientSocket, out, in);
        }
    }

    public static boolean captcha(Socket clientSocket, PrintWriter out, BufferedReader in) throws IOException {
        System.out.println("Captcha started");
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);

        out.println("It's good! To prove it let's take a little test: What is the sum of the numbers " + a + " and " + b + "?");
        String result = in.readLine();

        if (Integer.parseInt(result) == (a + b)) {
            out.println("Congrats! You are human! You can use our server");
            System.out.println("Access granted " + clientSocket.getLocalAddress());
            return true;
        } else {
            out.println("Sorry, this is the wrong answer. Try again?");
            System.out.println("Access denied " + clientSocket.getLocalAddress());
            captcha(clientSocket, out, in);
            return false;
        }
    }
}
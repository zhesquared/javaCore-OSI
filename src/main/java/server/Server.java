package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                    captcha(clientSocket, out, in);

                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

        public static void greetings (Socket clientSocket, PrintWriter out, BufferedReader in) throws IOException {
            out.println("Hello, user, what is your name?");
            String name = in.readLine();
            System.out.println("from client" + clientSocket.getLocalAddress() + ": " + name);
            out.println("Nice to meet you, " + name + "! You are robot? \"y/n\" ");
            char answer;

            //while (true)


        }

        public static boolean captcha (Socket clientSocket, PrintWriter out, BufferedReader in) throws IOException {
            String answer = in.readLine();

            return true;
        }
    }
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class Server {
    public static void main(String[] args) {
        try {
            ArrayList<Socket> userSocket = new ArrayList<>();
            ServerSocket serverSocket = new ServerSocket(8178);
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept(); // Ожидаем подключения клиента
                userSocket.add(socket);
                System.out.println("Клиент подключился");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream in = new DataInputStream(socket.getInputStream());
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            // Спрашиваете имя клиента
                            // Приветствуете клиента на сервере
                            // Записываете имя
                            while (true) {
                                String request = in.readUTF(); //Ожидаем сообщение от клиента
                                System.out.println("От клиента: " + request);
                                for (Socket socket1: userSocket) {
                                    DataOutputStream out1 = new DataOutputStream(socket1.getOutputStream());
                                    out1.writeUTF(request);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        }
            catch (IOException e) {
            e.printStackTrace();
        }
    }
}

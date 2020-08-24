package com.quick.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 先开服务器 再开客户端
 */
public class Server {
    // 服务器只需要开放端口。我们接受到别人发出的数据，也可以知道对方的IP地址
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("服务器准备就绪");
        System.out.println("服务器信息：" + serverSocket.getInetAddress() + " P:" + serverSocket.getLocalPort());

        // 接收多个客户端发送的数据
        while (true) {
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        }

    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("连接过来的客户端信息：" + socket.getInetAddress() + " P:" + socket.getLocalPort());
            try {
                // 获得打印流，用于数据输出，服务器回送数据
                BufferedWriter socketOutput = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                // 获得输入流，用于接受客户端的数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    String str = socketInput.readLine();
                    if ("bytes".equalsIgnoreCase(str)) {
                        this.flag = false;
                        socketOutput.write("byes");
                    } else {
                        // 打印收到的数据
                        System.err.println("服务器已接收到数据：" + str);
                        socketOutput.write("您发送的数据长度为: " + str.length());
                    }
                } while (flag);
                socketInput.close();
                socketOutput.close();
            } catch (Exception e) {
                System.out.println("连接异常断开");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("客户端已退出");
            }
        }
    }
}

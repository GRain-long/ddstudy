package com.quick.tcp;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        // 初始化socket
        Socket socket = new Socket();
        // 设置超时时间
        socket.setSoTimeout(3000);
        // 连接本地 端口2000 超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2222), 3000);
        System.out.println("已经向服务器发起了连接");
        System.out.println("客户端信息：" + socket.getLocalAddress() + ":" + socket.getLocalPort());

        try {
            // 发送数据
            todoMsg(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        } finally {
            socket.close();
            System.out.println("客户端关闭了");
        }
    }

    /**
     * @param client
     * @throws IOException
     * @思路 客户端需要发送数据，需要接受服务器端的数据。
     * 发送数据用输出流，接受数据用输入流
     * socket提供了两种流对象，一种是 输出流，用于向服务器发送数据
     * 一种是 输入流，用于接受服务器输入给客户端的数据
     */
    public static void todoMsg(Socket client) throws IOException {
        boolean flag = true;
        // 我们通过键盘录入 要发送的数据。
        BufferedReader consoleWriter = new BufferedReader(new InputStreamReader(System.in));
        // 客户端发送数据 的流对象
        BufferedWriter clientSend = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        // 客戶端接收 服务器的反馈
        BufferedReader serverRead = new BufferedReader(new InputStreamReader(client.getInputStream()));

        do {
            // 从键盘读取要发送的数据
            String sendMsg = consoleWriter.readLine();
            // 字符流写入数据 注意加换行符。服务器端接收用的readLine，读到换行符才算结束！不然会一次又一次的读下去！
            clientSend.write(sendMsg+"\r\n");
            // clientSend.newLine();
            clientSend.flush();

            String receive = serverRead.readLine();
            if ("bytes".equalsIgnoreCase(receive)) {
                flag = false;
            }
            System.out.println(receive);
        } while (flag);

    }


}



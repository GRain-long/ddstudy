package com.quick.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 内容提供者
 */
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider Started");
        // 监听20000端口是否有数据可接收。我们可以是数据的提供者，也可以接受数据
        DatagramSocket ds = new DatagramSocket(20000);
        // 构建接收实体
        byte[] buf = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        ds.receive(receivePacket);

        // 打印接收到的信息
        String ip = receivePacket.getAddress().getHostAddress();
        int port = receivePacket.getPort();
        int length = receivePacket.getLength();
        String msg = new String(receivePacket.getData(), 0, length);
        System.out.format("IP:%s ,Port:%d ,length:%d ,message:%s", ip, port, length, msg);

        // 回送一份数据
        String responseData = "I got your data";
        byte[] bytes = responseData.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(bytes, bytes.length,
                receivePacket.getAddress(),
                receivePacket.getPort());
        ds.send(responsePacket);
        System.out.println("I already response you");
        ds.close();
    }
}

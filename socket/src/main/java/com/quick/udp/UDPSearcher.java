package com.quick.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 用于搜索UDP消息
 */
public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started");
        // 作为搜索方，无需自己分配端口，让计算机为我们分配端口即可
        DatagramSocket ds = new DatagramSocket();

        // 发送一份数据
        String requestData = "Hello World Socket";
        byte[] bytes = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(bytes, bytes.length);
        // 给数据包设定好 发送给什么IP的什么端口
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        ds.send(requestPacket);

        System.out.println("I already give you data");
        // 构建接收实体
        byte[] buf = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        ds.receive(receivePacket);

        // 打印接收到的信息
        String ip = receivePacket.getAddress().getHostAddress();
        int port = receivePacket.getPort();
        int length = receivePacket.getLength();
        String msg = new String(receivePacket.getData(), 0, length);
        System.out.println("I search those data");
        System.out.format("IP:%s ,Port:%d ,length:%d ,message:%s", ip, port, length, msg);

        ds.close();
    }
}

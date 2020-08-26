package com.quick.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收方，监听固定的端口，看是否有广播的信息。
 */
public class UDPListener {
    public static void main(String[] args) throws IOException {
        DatagramSocket receive = new DatagramSocket(20000);
        byte[] receiveMsg = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(receiveMsg, receiveMsg.length);
        receive.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        System.out.println(data.length);
        String msg = new String(data, 0, data.length);
        System.out.println(msg);
        System.out.println("我接收到广播的消息了！");
        System.out.format("Message:%s, Port:%d, Address:%s", msg, datagramPacket.getPort(), datagramPacket.getAddress());
        receive.close();
    }
}

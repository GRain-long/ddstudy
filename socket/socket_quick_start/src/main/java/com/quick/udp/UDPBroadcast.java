package com.quick.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP广播方。
 * 把数据进行广播。
 */
public class UDPBroadcast {
    public static void main(String[] args) throws IOException {
        DatagramSocket broadcast = new DatagramSocket();
        byte[] broadMessage = "I send a broadcast！".getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(
                broadMessage,
                broadMessage.length);
        datagramPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        datagramPacket.setPort(20000);
        broadcast.setBroadcast(true);
        broadcast.send(datagramPacket);
        broadcast.close();
    }
}

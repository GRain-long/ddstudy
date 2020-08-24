package com.quick.udp.search;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 用于搜索UDP消息
 */
public class UDPSearcher {
    private static final int LISTEN_PORT = 30000;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("UDPSearcher Started.");
        Listener listener = listen();
        sendBroadcast();
        System.in.read();

        List<Device> devices = listener.getDevicesAndClose();

        for (Device device : devices) {
            System.out.println("Device:" + device.toString());
        }

        // 完成
        System.out.println("UDPSearcher Finished.");


    }

    private static Listener listen() throws InterruptedException {
        System.out.println("UDPSearcher start listen.");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISTEN_PORT, countDownLatch);
        listener.start();

        countDownLatch.await();
        return listener;
    }

    private static void sendBroadcast() throws IOException {
        System.out.println("UDPSearcher sendBroadcast started");

        // 作为搜索方，无需自己分配端口，让计算机为我们分配端口即可
        DatagramSocket ds = new DatagramSocket();



        // 发送一份数据
        String requestData = MessageCreator.buildWithPort(LISTEN_PORT);
        byte[] requestDataBytes = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);

        // 20000端口, 广播地址
        requestPacket.setAddress(InetAddress.getByName("2555.255.255.255"));
        requestPacket.setPort(20000);

        // 发送数据
        ds.send(requestPacket);
        ds.close();

        // 完成提示
        System.out.println("UDPSearcher sendBroadcast finished");

    }

    private static class Device {
        final int port;
        final String ip;
        final String sn;

        public Device(int port, String ip, String sn) {
            this.port = port;
            this.ip = ip;
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", ip='" + ip + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    private static class Listener extends Thread {
        private final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> devices = new ArrayList<>();
        private boolean done = false;
        private DatagramSocket ds = null;

        public Listener(int listenPort, CountDownLatch countDownLatch) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                // 监听回送端口
                ds = new DatagramSocket(listenPort);


                while (!done) {
                    // 构建接收实体
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

                    // 接收
                    ds.receive(receivePack);

                    // 打印接收到的信息与发送者的信息
                    // 发送者的IP地址
                    String ip = receivePack.getAddress().getHostAddress();
                    int port = receivePack.getPort();
                    int dataLen = receivePack.getLength();
                    String data = new String(receivePack.getData(), 0, dataLen);
                    System.out.println("UDPSearcher receive form ip:" + ip
                            + "\tport:" + port + "\tdata:" + data);

                    String sn = MessageCreator.parseSN(data);
                    if (sn != null) {
                        Device device = new Device(port, ip, sn);
                        devices.add(device);
                    }
                }
            } catch (Exception ignored) {

            } finally {
                close();
            }
            System.out.println("UDPSearcher listener finished.");
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        private List<Device> getDevicesAndClose() {
            done = true;
            close();
            return devices;
        }
    }

}

package com.quick.udp.search;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

/**
 * 内容提供者
 */
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        // 随机生成一份标识
        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn);
        provider.start();

        // 收到任意数据后退出
        System.in.read();
        provider.exit();

    }

    private static class Provider extends Thread {
        private final String sn;
        private boolean done = false;
        private DatagramSocket ds = null;

        public Provider(String sn) {
            this.sn = sn;
        }

        @Override
        public void run() {

            System.out.println("UDPProvider Started.");
            try {
                // 监听20000端口是否有数据可接收。我们可以是数据的提供者，也可以接受数据
                ds = new DatagramSocket(20000);

                while (!done) {

                    // 构建接收实体
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                    // 接受数据
                    ds.receive(receivePacket);

                    // 打印接收到的信息
                    String ip = receivePacket.getAddress().getHostAddress();
                    int port = receivePacket.getPort();
                    int length = receivePacket.getLength();
                    String msg = new String(receivePacket.getData(), 0, length);
                    System.out.format("UDPProvider receive form IP:%s ,Port:%d ,length:%d ,message:%s", ip, port, length, msg);

                    // 解析端口号
                    int responsePort = MessageCreator.parsePort(msg);
                    if (responsePort != -1) {
                        // 构建一份回送数据
                        String responseData = "I got your data";
                        byte[] bytes = responseData.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(bytes, bytes.length,
                                receivePacket.getAddress(),
                                receivePacket.getPort());

                        ds.send(responsePacket);
                    }
                }
            } catch (Exception e) {
            } finally {
                close();
            }
            System.out.println("UDPProvider Finished");
        }


        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        void exit() {
            done = true;
            close();
        }


    }
}

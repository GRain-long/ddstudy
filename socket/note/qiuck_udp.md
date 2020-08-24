# 引言
## UDP是什么
- UDP：User Datagram Protocol 缩写为UDP
- 一种用户数据报协议，又称用户数据报文协议
- 是一个简单的，面向数据报的传输层协议正式规范为RFC 768
- 用户数据协议、非连接协议

## UDP的特点
- UDP是不可靠的，UDP只管发送或接收，不考虑数据是否发送出去了，无重传机制。
- 发送端生产数据，接收端从网络中抓取数据

## UDP的应用
- DNS、TFTP、SNMP
- 视频、音频、普通数据（数据可以不可靠）

## UDP包最大长度
- 16位 --> 2字节 存储长度信息
- 2^16 -1 = 64K - 1 = 65536 -1 = 65535
- 自身协议占用：32+32 = 64位 = 8字节
- 65535-8=65507byte

# UDP核心API
## DatagramSocket
- 用于接收与发送UDP的类
- 负责发送/接收 一个UDP包。
- 不同于TCP，UDP并没有合并到Socket API中
- 既是服务器，又是客户端。
- DatagramSocket() 创建简单实例，不指定端口与IP，会自动复用本地可用的端口进行数据的发送
- DatagramSocket(int port) 监听固定端口，用于监听某一端口的。只是监听哦！只能指定接收数据的端口
- DatagramSocket(int port,InetAddress localAddr) 创建固定端口并指定IP
- receive(DatagramPacket d): 接收
- send(DatagramPacket d): 发送
- setSoTimeout(int timeout): 设置超时，毫秒
- close(): 关闭 释放资源

## DatagramPacket
- 用于处理报文
- 将byte数组、目标地址、目标端口等数据包装成报文或者将报文拆卸成byte数组
- UDP的发送实体，接收实体。
- DatagramPacket(byte[]buf, int offset, int length, InetAddress address, int port)
    - address指定目标机器地址 port指定目标机器端口
- DatagramPacket(byte[]buf, int offset, int length,SocketAddress address)
    - SocketAddress相当于InetAddress+Port
- setData(byte[]buf,int offset,int length)
- setData(byte[]buf)
- setLength(int length)
- getData(),getOffset(),getLength()

## UDP 单播、广播、多播
- 单播：一对一
- 广播：一对所有
- 多播：一对多，会进行一定的筛选
### 广播地址
- 255.255.255.255 为受限的广播地址
- C网广播地址一般为：192.xxx.xxx.255 如（192.168.1.255）算了，我不懂，之后补充

## 局域网搜索
- UDP接收消息并回送功能实现
- UDP局域网广播发送消息
- UDP局域网接收广播信息

建议看UDPBroadcast 和 UDPListener再看UDPProvider+UDPSearcher

UDP既是客户端又是服务端。即可发送消息也可接收消息。

可以用UDP进行广播，让其他UDP进行局域搜索接收消息。

UDP包中有存储发送源的端口，已经接收的目标源的端口。
发送端口可能是随机的，接收目标源的端口是确定的。

UDP是监听端口，看被监听的端口是否有数据！

UDP发送数据包，数据包中有发送方的IP地址和端口号
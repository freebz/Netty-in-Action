// 예제 1.3 비동기 연경

Channel channel = ...;
// 블로킹하지 않음
ChannelFuture future = channel.connect(
    new InetSocketAddress("192.168.0.1", 25));

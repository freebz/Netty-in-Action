// 예제 8.9 정상 종료

EventLoopGroup group = new NioEventLoopGroup();
Bootstrap bootstrap = new Bootstrap();
bootstrap.group(group)
    .channel(NioSocketChannel.class);
...
Future<?> future = group.shutdownGracefully();
// group이 종료될 때까지 진행을 블로킹함
future.syncUninterruptibly();

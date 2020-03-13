// 예제 8.6 ChannelInitializer를 이용한 부트스트랩

ServerBootstrap bootstrap = new ServerBootstrap();
bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
    .channel(NioServerSocketChannel.class)
    .childHandler(new ChannelInitializerImpl());
ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
future.sync();
final class ChannelInitializerImpl extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPIpeline pipeline = ch.pipeline();
	pipeline.addLast(new HttpClientCode());
	pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
    }
}

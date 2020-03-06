// 예제 4.4 네티를 이용하는 비동기 네트워킹

public class NettyNioServer {
    public void server(int port) throws Exception {
	final ByteBuf buf = Unpooled.copiedBuffer("Hi!\r\n",
	    Charset.forName("UTF-8"));
	EventLoopGroup group = new NioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(group).channel(NioServerSocketChannel.class)
		.localAddress(new InetSocketAddress(port))
		.childHandler(new ChannelInitializer<SocketChannel>() {
		    @Override
		    public void initChannel(SocketChannel ch)
			throws Exception {
			ch.pipeline().addLast(
			    new ChannelInboundHandlerAdapter() {
				@Override
				public void channelActive(
				    ChannelHandlerContext ctx) throws Exception {
				    ctx.writeAndFlush(buf.duplicate())
					.addListener(
					    ChannelFutureListener.CLOSE);
				}
			});
		    }
	        });
	    ChannelFuture f = b.bind().sync();
	    f.channel().clseFuture().sync();
	} finally {
	    group.shutdownGracefully().sync();
	}
    }
}

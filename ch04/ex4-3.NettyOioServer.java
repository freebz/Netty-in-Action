// 예제 4.3 네티를 이용하는 블로킹 네트워킹

public class NettyOioServer {
    public void server(int port) throws Exception {
	final ByteBuf buf = Unpooled.unreleasableBuffer(
	    Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
	EventLoopGroup group = new OioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(group)
		.channel(OioServerSocketChannel.class)
		.localAddress(new InetSocketAddress(port))
		.childHandler(new ChannelInitializer<SocketChannel>() {
		    @Override
		    public void initChannel(SocketChannel ch)
			throws Exception {
			ch.pipeline().addLast(
			    new ChannelInboundHandlerAdapter() {
				@Override
				public void channelActive(
				    ChannelHandlerContext ctx)
				        throws Exception {
				    ctx.writeAndFlush(buf.duplicate())
					.addListener(
					    ChannelFutureListener.CLOSE);
				}
			    });
		    }
	        });
	    ChannelFuture f = b.bind().sync();
	    f.channel().closeFuture().sync();
	} finally {
	    group.shutdownGracefully().syn();
	}
    }
}

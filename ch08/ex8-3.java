// 예제 8.3 호환되지 않는 Channel과 EventLoopGroup

EventLoopGroup group = new NioEventLoopGroup();
Bootstrap bootstrap = new Bootstrap();
bootstrap.group(group)
    .channel(OioSocketChannel.class)
    .handler(new SimpleChannelInboundHandler<ByteBuf>() {
	@Override
	protected void channelRead0(
	    ChannelHandlerContext channelHandlerContext,
	    ByteBuf byteBuf) throws Exception {
	    System.out.println("Received data");
	}
    } );
ChannelFuture future = bootstrap.connect(
    new InetSocketAddress("www.manning.com", 80));
future.syncUninterruptibly();

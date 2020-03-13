// 예제 8.1 클라이언트 부트스트랩

EventLoopGroup group = new NioEventLoopGroup();
Bootstrap bootstrap = new Bootstrap();
bootstrap.group(group)
    .channel(NioSocketChannel.class)
    .handler(new SimpleChannelInboundHandler<ByteBuf>() {
	@Override
	protected void channelRead0(
	    ChannelHandlerContext channelHandlerContext,
	    ByteBuf byteBuf) throws Exception {
	    System.out.println("Received data");
	}
    } );
ChannelFuture future = bootstrap.connect(
    @Override
    public void operationComplete(ChannelFuture channelFuture)
        throws Exception {
	if (channelFuture.isSuccess()) {
	    System.out.println("Connection established");
	} else {
	    System.err.println("Connection attempt failed");
	    channelFuture.cause().printStackTrace();
	}
    }
} );

// 예제 8.4 서버 부트스트랩

NioEventLoopGroup group = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap();
bootstrap.group(group)
    .channel(NioServerSocketChannel.class)
    .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
	    ByteBuf byteBuf) throws Exception {
	    System.out.println("Received data");
	}
    } );
ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
future.addListener(new ChannelFutureListener()
{
    @Override
    public void operationComplete(ChannelFuture channelFuture)
	throws Exception {
	if (channelFuture.isSuccess()) {
	    System.out.println("Server bound");
	} else {
	    System.err.println("Bound attempt failed");
	    channelFuture.cause().printStackTrace();
	}
    }
} );

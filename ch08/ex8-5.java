// 예제 8.5 서버 부트스트랩

ServerBootstrap bootstrap = new ServerBootstrap();
bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
    .channel(NioServerSocketChannel.class)
    .childHandler(
	new SimpleChannelInboundHandler<ByteBuf>() {
	    ChannelFuture connectFuture;
	    @Override
	    public void channelActive(ChannelHandlerContext ctx)
		throws Exception {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.channel(NioSocketChannel.class).handler(
		    new SimpleChannelInboundHandler<ByteBuf>() {
			@Override
			protected void channelRead0(
			    ChannelHandlerContext ctx, ByteBuf in)
			    throws Exception {
			    System.out.println("Received data");
			}
		    } );
		bootstrap.group(ctx.channel().eventLoop());
		connectFuture = bootstrap.connect(
		    new InetSocketAddress("www.manning.com", 80));
	    }
	    @Override
	    protected void channelRead0(
		ChannelHandlerContext channelHandlerContext,
		    ByteBuf byteBuf) throws Exception {
		if (connectFuture.isDone()) {
		    // 데이터를 이용해 필요한 일을 함
		}
	    }
	} );
ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
future.addListener(new ChannelFutureListener() {
    @Override
    public void operationComplete(ChannelFuture channelFuture)
	throws Exception {
	if (channelFuture.isSuccess()) {
	    System.out.println("Server bound");
	} else {
	    System.err.println("Bind attempt failed");
	    channelFuture.cause().printStackTrace();
	}
    }
} );

// 예제 8.7 특성 이용

final AttributeKey<Integer> id = new AttributeKey<Integer>("ID");
Bootstrap bootstrap = new Bootstrap();
bootstrap.group(new NioEventLoopGroup())
    .channel(NioSocketChannel.class)
    .handler(
	new SimpleChannelInboundHandler<ByteBuf>() {
	    @Override
	    public void channelRegistered(ChannelHandlerContext ctx)
		throws Exception {
		Integer idValue = ctx.channel().attr(id).get();
		// idValue를 이용해 필요한 일을 함
	    }
	    @Override
	    protected void channelRead0(
		ChannelHandlerContext channelHandlerContext,
		ByteBuf byteBuf) throws Exception {
		System.out.println("Received data");
	    }
	}
    );
bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
bootstrap.attr(id, 123456);
ChannelFuture future = bootstrap.connect(
    new InetSocketAddress("www.manning.com", 80));
future.syncUninterruptibly();

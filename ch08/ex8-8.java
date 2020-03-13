// 예제 8.8 Bootstrap을 DatagramChannel과 함께 이용

Bootstrap bootstrap = new Bootstrap();
bootstrap.group(new OioEventLoopGroup()).channel(
    OioDatagramChannel.class).handler(
	new SimpleChannelInboundHandler<DatagramPacket>() {
	    @Override
	    public void channelRead0(ChannelHandlerContext ctx,
		DatagramPacket msg) throws Exception {
		// 패킷을 이용해 필요한 일을 함
	    }
	}
    );
ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
future.addListener(new ChannelFutureListener() {
    @Override
    public void operationComplete(ChannelFuture channelFuture)
	throws Exception {
	if (channelFuture.isSuccess()) {
	    System.out.println("Channel bound");
	} else {
	    System.err.println("Bind attempt failed");
	    channelFuture.cause().printStackTrace();
	}
    }
});

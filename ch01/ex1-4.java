// 예제1.4 콜백을 이용하는 방법

Channel channel = ...;
// 블로킹하지 않음
ChannelFuture future = channel.connect(
    new InetSocketAddress("192.168.0.1", 25));
future.addListener(new ChannelFutureListener() {

    @Override
    public void operationComplete(ChannelFuture future) {
	if (future.isSuccess()) {
	    ByteBuf buffer = Unpooled.copiedButter(
		"Hello", Charset.defaultCharset());
	    ChannelFuture wf = future.channel()
		.writeAndFlush(buffer);

	    ...
	} else {
	    Throwable cause = future.cause();
	    cause.printStackTrace();
	}
    }
});

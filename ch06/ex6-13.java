// 예제 6.13 ChannelFutureListener를 ChannelFuture에 추가

ChannelFuture future = channel.write(someMessage);
future.addListener(new ChannelFutureListener() {
    @Override
    public void operationComplete(ChannelFuture f) {
	if (!f.isSuccess()) {
	    f.cause().printStackTrace();
	    f.channel().close();
	}
    }
});

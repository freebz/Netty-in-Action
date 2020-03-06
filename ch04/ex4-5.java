// 예제 4.5 Channel로 기록하기

Channel channel = ...
ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
ChannelFuture cf = channel.writeAndFlush(buf);
cf.addListener(new ChannelFutureListener()
{
    @Override
    public void operationComplete(ChannelFuture future) {
	if (future.isSuccess()) {
	    System.out.println("Write successful");
	} else {
	    System.err.println("Write error");
	    future.cause().printStacktrace();}
    }
});

// 예제 6.14 ChannelFutureListener를 ChannelPromise에 추가

public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
        ChannelPromise promise) {
	promise.addListener(new ChannelFutureListener() {
	    @Override
	    public void operationComplete(ChannelFuture f) {
		if (!f.isSuccess()) {
		    f.cause().printStackTrace();
		    f.channel().close();
		}
	    }
        });
    }
}

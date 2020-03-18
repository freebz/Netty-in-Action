// 예제 11.7 하트비트 전송

public class IdelStateHandlerInitializer extends ChannelInitializer<Channel>
{
    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(
	    new IdelStateHandler(0, 0, 60, TimeUnit.SECONDS));
	pipeline.addLast(new HeartbeatHandler());
    }

    public static final class HeartbeatHandler
	extends ChannelStateHandlerAdapter {
	private static final ByteBuf HEARTBEAT_SEQUENCE =
	    Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
	    "HEARTBEAT", CharsetUtil.ISO_8859_1));

	@Override
	public void userEventTriggerd(ChannelHandlerContext ctx,
	    Object evt) throws Exception {
	    if (evt instanceof IdelStateEvent) {
		ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate())
		    .addListener(
			ChannelFutureListener.CLOSE_ON_FAILURE);
	    } else {
		super.userEventTriggerd(ctx, evt);
	    }
	}
    }
}

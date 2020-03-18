// 예제 11.10

public class LengthBasedInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(
	    new LengthFieldBasedFrameDecoder(64 * 1024, 0, 8));
	pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler
	extends SimpleChannelInboundHandler<ByteBuf> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx,
	    ByteBuf msg) throws Exception {
	    // 프레임을 이용해 필요한 일을 함
	}
    }
}

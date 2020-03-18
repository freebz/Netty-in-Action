// 예제 11.14 protobuf 이용

public class ProtoBufInitializer extends ChannelInitializer<Channel> {
    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite) {
	this.lite = lite;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(new ProtobufVarint32FrameDecoder());
	pipeline.addLast(new ProtobufEncoder());
	pipeline.addLast(new ProtobufDecoder(lite));
	pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler
	extends SimpleChannelInboundHandler<Object> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	    // 객체를 이용해 필요한 일을 수행
	}
    }
}

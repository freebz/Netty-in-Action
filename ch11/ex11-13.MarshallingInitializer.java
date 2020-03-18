// 예제 11.13 JBoss 마셜링 이용

public class MarshallingInitializer extends ChannelInitializer<Channel> {
    private final MarshallerProvider marshallerProvider;
    private final UnmarshallerProvider unmarshallerProvider;

    public MarshallingInitializer(
	UnmarshallerProvider unmarshallerProvider,
	MarshallerProvider marshallerProvider) {
	this.marshallerProvider = marshallerProvider;
	this.unmarshallerProvider = unmarshallerProvider;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
	ChannelPipeline pipeline = channel.pipeline();
	pipeline.addLast(new MarshallingDecoder(unmarshallerProvider));
	pipeline.addLast(new MarshallingEncoder(marshallerProvider));
	pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler
	extends SimpleChannelInboundHandler<Serializable> {
	@Override
	public void channelRead0(
	    ChannelHandlerContext = channelHandlerContext,
	    Serializable serializable) throws Exception {
	    // 필요한 작업을 수행
	}
    }
}

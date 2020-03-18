// 예제 12.6 ChannelPipeline에 암호화 추가

public class SecureChatServerInitializer extends ChatServerInitializer {
    private final SslContext context;

    public SecureChatServerInitializer(ChannelGroup group,
	SslContext context) {
	super(group);
	this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	super.initChannel(ch);
	SSLEngine engine = context.newEngine(ch.alloc());
	ch.pipeline().addFirst(new SslHandler(engine));
    }
}

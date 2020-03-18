// 예제 11.1 SSL/TLS 지원 추가

public class SslChannelInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean startTls;

    public SslChannelInitializer(SslContext context,
	boolean startTls) {
	this.context = context;
	this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	SSLEngine engine = context.newEngine(ch.alloc());
	ch.pipeline().addFirst("ssl",
            new SslHandler(engine, startTls));
    }
}

// 예제 11.5 HTTPS 이용

publici class HttpsCodecInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean isClient;

    public HttpsCodecInitializer(SslContext context, boolean isClient) {
	this.context = context;
	this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	SSLEngine engine = context.newEngine(ch.alloc());
	pipeline.addFirst("ssl", new SslHandler(engine));

	if (isClinet) {
	    pipeline.addLast("codec", new HttpClinetCodec());
	} else {
	    pipeline.addLast("codec", new HttpServerCodec());
	}
    }
}

// 예제 11.3 HTTP 메시지 조각을 자동으로 집계

public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
	this.isClient = isClinet;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	if (isClinet) {
	    pipeline.addLast("codec", new HttpClientCodec());
	} else {
	    pipeline.addLast("codec", new HttpServerCodec());
	}
	pipeline.addLast("aggregator",
	    new HttpObjectAggregator(512 * 1024));
    }
}

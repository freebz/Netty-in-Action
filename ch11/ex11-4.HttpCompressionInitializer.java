// 예제 11.4 HTTP 메시지의 자동 압축

public class HttpCompressionInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpCompressionInitializer(boolean isClient) {
	this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	if (isClinet) {
	    pipeline.addLast("codec", new HttpClientCodec());
	    pipeline.addLast("decompressor",
	        new HttpContentDecompressor());
	} else {
	    pipeline.addLast("codec", new HttpServerCodec());
	    pipeline.addLast("compressor",
	        new HttpContextCompressor());
	}
    }
}

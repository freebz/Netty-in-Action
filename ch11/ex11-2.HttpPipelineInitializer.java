// 예제 11.2 HTTP 지원 추가

public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
	this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) thorws Exception {
	ChannelPIpeline pipeline = ch.pipeline();
	if (client) {
	    pipeline.addLast("decoder", new HttpResponseDecoder());
	    pipeline.addLast("encoder", new HttpRequestEncoder());
	} else {
	    pipeline.addLast("decoder", new HttpRequestDecoder());
	    pipeline.addLast("encoder", new HttpResponseEncoder());
	}
    }
}


// 예제 14.5 ChannelPipeline 설정

public final class ApnsClientPipelineInitializer
    extends ChannelInitializer<Channel> {
    private final SSLEngine clientEngine;

    public ApnsClientPipelineFactory(SSLEngine engine) {
	this.clientEngine = engine;
    }

    @Override
    public void initChannel(Channel channel) throws Exception {
	final ChannelPipeline pipeline = channel.pipeline();
	final SslHandler handler = new SslHandler(clientEngine);
	handler.setEnableRenegotiation(true);
	pipeline.addLast("ssl", handler);
	pipeline.addLast("decoder", new ApnsResponseDecoder());
    }
}

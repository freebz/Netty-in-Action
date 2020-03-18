// 예제 12.3 ChannelPipeline 초기화

public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;

    public ChatServerInitializer(ChannelGroup group) {
	this.group = group;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(new HttpServerCodec());
	pipeline.addLast(new ChunkedWriteHandler());
	pipeline.addLast(new HttpObjectAggregator(64 * 1024));
	pipeline.addLast(new HttpRequestHandler("/ws"));
	pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
	pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}

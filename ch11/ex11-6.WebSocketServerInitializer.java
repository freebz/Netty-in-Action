// 예제 11.6 서버에서 웹소켓 지원

public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
	ch.pipeline().addLast(
	    new HttpServerCodec(),
	    new HttpObjectAggregator(65536),
	    new WebSocketServerProtocolHandler("/websocket"),
	    new TextFrameHandler(),
	    new BinaryFrameHandler(),
	    new ContinuationFrameHandler());
    }
    public static final class TextFrameHandler extends
	SimpleChannelInboundHandler<TextWebSocketFrame> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx,
	    TextWebSocketFrame msg) throws Exception {
	    // 텍스트 프레임 처리
	}
    }
    public static final class BinaryFrameHandler extends
	SimpleChannelInboundHandler<BinaryWebSocketFrame> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx,
	    BinaryWebSocketFrame msg) throws Exception {
	    // 이진 프레임 처리
	}
    }
    public static final class ContinuationFrameHandler extends
	SimpleChannelInboundHandler<ContinuationWebSocketFrame> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx,
	    ContinuationWebSocketFrame msg) throws Exception {
	    // 지속 프레임 처리
	}
    }
}

// 예제 12.2 텍스트 프레임의 처리

public class TextWebSocketFrameHandler
    extends SimpleChannelInboundHandler<TextWEbSocketFrame> {
    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
	this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx,
	Object evt) throws Exception {
	if (evt == WebSocketServerProtocolHandler
	    .ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
	    ctx.pipeline().remove(HttpRequestHandler.class);
	    group.writeAndFlush(new TextWebSocketFrame(
		"Client " + ctx.channel() + " joined"));
	    group.add(ctx.channel());

	} else {
	    super.userEventTriggered(ctx, evt);
	}
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,
	TextWebSocketFrame msg) throws Exception {
	group.writeAndFlush(msg.retain());
    }
}

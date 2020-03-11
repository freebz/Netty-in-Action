// 예제 6.2 SimpleChannelInboundHandler 이용

@Sharable
public class SimpleDiscardHandler
    extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
        Object msg) {
	// 다를 조치를 취할 필요가 없음
    }
}

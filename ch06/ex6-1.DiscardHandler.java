// 예제 6.1 메시지 리소스 해제

@Sharable
public class DiscardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	ReferenceCountUtil.release(msg);
    }
}

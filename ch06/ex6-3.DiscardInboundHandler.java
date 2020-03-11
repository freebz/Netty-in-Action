// 예제 6.3 인타운드 메시지를 소비 및 해제하는 방법

@Sharable
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	ReferenceCountUitl.release(msg);
    }
}

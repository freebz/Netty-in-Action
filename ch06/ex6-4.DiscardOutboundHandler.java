// 예제 6.4 아웃바운드 데이터의 페기와 해제

@Sharable
public class DiscardOutboundHandler
    extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx,
        Object msg, ChannelPromise promise) {
	ReferenceCountUitl.release(msg);
	promise.setSuccess();
    }
}

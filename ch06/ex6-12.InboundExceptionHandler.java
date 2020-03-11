// 예제 6.12 기본 인바운드 예외 처리

public class InboundExceptionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
	Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}

// 예제 6.11 @Sharable을 잘못 이용한 예

@Sharable
public class UnsharableHandler extends ChannelInboundHandlerAdapter {
    private int count;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	count++;
	System.out.println("channelRead(...) called the "
	    + count + " time");
	ctx.fireChannelRead(msg);
    }
}

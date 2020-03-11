// 예제 6.10 공유 가능한 ChannelHandler

@Sharable
public class SharableHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	System.out.println("Channel read message: " + msg);
	ctx.fireChannelRead(msg);
    }
}

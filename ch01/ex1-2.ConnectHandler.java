// 예제 1.2 콜백에 의해 트리거되는 ChannelHandler

public class ConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx)
	throws Exception {
	System.out.println(
	    "Client " + ctx.channel().remoteAddress() + " connected");
    }
}

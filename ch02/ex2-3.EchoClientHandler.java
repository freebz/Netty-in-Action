// 예제 2.3 클라이언트의 ChannelHandler

@Sharable
public class EchoClientHandler extends
    SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
	ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
        CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
	System.out.println(
	    "Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
	Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}

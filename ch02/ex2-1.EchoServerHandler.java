// 예제 2.1 EchoServerHandler

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object map) {
	ByteBuf in = (ByteBuf) msg;
	System.out.println(
	    "Server received: " + in.toString(CharseUtil.UTF_8));
	ctx.write(in);
    }

    @Override
    public void channelReacComplete(ChannelHandlerContext ctx) {
	ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
	    .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
	Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}

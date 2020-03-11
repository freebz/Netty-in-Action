// 예제 6.9 ChannelHandlerContext를 캐싱하고 이용하는 방법

public class WriteHandler extends ChannelHandlerAdapter {
    private ChannelHandlerContext ctx;
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
	this.ctx = ctx;
    }
    public void send(String msg) {
	ctx.writeAndFlush(msg);
    }
}

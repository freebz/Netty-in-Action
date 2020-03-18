// 예제 13.7 LogEventHandler

public class LogEventHandler
    extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
	Throwable cause) throws Exception {
	cause.rpintStackTrace();
	ctx.close();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,
	LogEvent event) throws Exception {
	StringBuilder builder = new StringBuilder();
	builder.append(event.getReceivedTimestamp());
	builder.append(" [");
	builder.append(event.getSource().toString());
	builder.append("] [");
	builder.append(event.getLogfile());
	builder.append("] : ");
	builder.append(event.getMsg());
	System.out.println(builder.toSTring());
    }
}

// 예제 13.6 LogEventDecoder

public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx,
	DatagramPacket datagramPacket, List<Object> out) throws Exception {
	ByteBuf data = datagramPacket.data();
	int idx = data.indexOf(0, data.readableBytes(),
	    LogEvent.SEPARATOR);
	String filename = data.slice(0, idx)
	    .toString(CharsetUtil.UTF_8);
	String logMsg = data.slice(idx + 1,
	    data.readableBytes()).toString(CharsetUtil.UTF_8);

	LogEvent event = new LogEvent(datagramPacket.removeAddress(),
	    System.currentTimeMillis(), filename, logMsg);
	out.add(event);
    }
}

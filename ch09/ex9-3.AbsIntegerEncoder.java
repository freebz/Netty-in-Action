// 예제 9.3 AbsIntegerEncoder

public class AbsIntegerEncoder extends
    MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
	ByteBuf in, List<Object> out) throws Exception {
	while (in.readableBytes() >= 4) {
	    int value = Math.abs(in.readInt());
	    out.add(value);
	}
    }
}

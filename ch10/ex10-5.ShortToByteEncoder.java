// 예제 10.5 ShortToByteEncoder 클래스

public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out)
	throws Exception {
	out.writeShort(msg);
    }
}

// 예제 10.9 CharToByteEncoder 클래스

public class CharToByteEncoder extends
    MessageToByteEncoder<Character> {
    @Override
    public void encode(ChannelHandlerContext ctx, Character msg,
	ByteBuf out) throws Exception {
	out.writeChar(msg);
    }
}

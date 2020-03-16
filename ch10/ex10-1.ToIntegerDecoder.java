// 예제 10.1 ByteToMessageDecoder를 확장하는 ToIntegerDecoder 클래스

public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object> out) throws Exception {
	if (in.readableBytes() >= 4) {
	    out.add(in.readInt());
	}
    }
}

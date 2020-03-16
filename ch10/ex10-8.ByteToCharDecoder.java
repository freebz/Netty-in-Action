// 예제 10.8 ByteToCharDecoder 클래스

public class ByteToCharDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object> out) throws Exception {
	while (in.readableBytes() >= 2) {
	    out.add(in.readChar());
	}
    }
}

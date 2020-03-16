// 예제 10.2 ReplayingDecoder를 확장하는 ToIntegerDecoder2 클래스

public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object> out) throws Exception {
	out.add(in.readInt());
    }
}

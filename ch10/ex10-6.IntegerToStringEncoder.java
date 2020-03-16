// 예제 10.6 IntegerToStringEncoder 클래스

public class IntegerToStringEncoder
    extends MessageToMessageEncoder<Integer> {
    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg,
	List<Object> out) throws Exception {
	out.add(String.valueOf(msg));
    }
}

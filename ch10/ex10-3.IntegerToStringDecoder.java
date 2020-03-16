// 예제 10.3 IntegerToStringDecoder 클래스

public class IntegerToStringDecoder extends
    MessageToMessageDecoder<Integer> {
    @Override
    public void decode(ChannelHandlerContext ctx, Integer msg,
	List<Object> out) throws Exception {
	out.add(String.valueOf(msg));
    }
}

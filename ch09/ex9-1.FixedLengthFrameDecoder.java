// 예제 9.1 FixedLengthFrameDecoder

public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
	if (frameLength <= 0) {
	    throw new IllegalArgumentException(
		"frameLength must be a positive integer: " + frameLength);
	}
	this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object out) throws Exception {
	while (in.readableBytes() >= frameLength) {
	    ByteBuf buf = in.readBytes(frameLength);
	    out.add(buf);
	}
    }
}

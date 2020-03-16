// 예제 10.4 TooLongFrameException

public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
    private static final int MAX_FRAME_SIZE = 1024;
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object> out) throws Exception {
	int readable = in.readableBytes();
	if (readable > MAX_FRAME_SIZE) {
	    in.skipBytes(readable);
	    throw new TooLongFrameException("Frame too big!");
	}
	// 필요한 작업을 수행
	...
    }
}

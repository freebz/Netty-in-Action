// 예제 9.5 FrameChunkDecoder

public class FrameChunkDecoder extends ByteToMessageDecoder {
    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
	this.maxFrameSize = maxFrameSize;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
	List<Object> out) throws Exception {
	int readableBytes = in.readableBytes();
	if (readableBytes > maxFrameSize) {
	    // 바이트를 폐기
	    in.clear();
	    throw new TooLongFrameException();
	}
	ByteBuf buf = in.readBytes(readableBytes);
	out.add(buf);
    }
}

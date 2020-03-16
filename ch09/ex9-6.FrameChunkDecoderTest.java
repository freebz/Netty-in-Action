// 예제 9.6 FrameChunkDecoder 테스트

public class FrameChunkDecoderTest {
    @Test
    public void testFramesDecoded() {
	ByteBuf buf = Unpooled.buffer();
	for (int i = 0; i < 9; i++) {
	    buf.writeByte(i);
	}
	ByteBuf input = buf.duplicate();

	EmbeddedChannel channel = new EmbeddedChannel(
	    new FrameChunkDecoder(3));

	assertTrue(channel.writeInbound(input.readBytes(2)));
	try {
	    channel.writeInbound(input.readBytes(4));
	    Assert.fail();
	} catch (TooLongFrameException e) {
	    // 예상된 예외
	}
	assertTrue(channel.writeInbound(input.readBytes(3)));
	assertTrue(channel.finish());

	// 프레임을 읽음
	ByteBuf read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(2), read);
	read.release();

	read = (ByteBuf) channel.readInbound();
	assertEquals(buf.skipBytes(4).readSlice(3), read);
	read.release();
	buf.release();
    }
}

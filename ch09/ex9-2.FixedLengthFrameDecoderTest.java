// 예제 9.2 FixedLengthFrameDecoder 테스트

public class FixedLengthFrameDecoderTest {
    @Test
    public void testFramesDecoded() {
	ByteBuf buf = Unpoooled.buffer();
	for (int i = 0; i < 9; i++) {
	    buf.writeByte(i);
	}
	ByteBuf input = buf.duplicate();
	EmbeddedChannel channel = new EmbeddedChannel(
	    new FixedLengthFrameDecoder(3));
	// 바이트를 기록
	assertTrue(channel.writeInbound(input.retain()));
	assertTrue(channel.finish());

	// 메시지를 읽음
	ByteBuf read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();

	read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();

	read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();

	assertNull(channel.readInbound());
	buf.release();
    }

    @Test
    public void testFramesDecode2() {
	ByteBuf buf = Unpooled.buffer();
	for (int i = 0; i < 9; i++) {
	    buf.writeByte(i);
	}
	ByteBuf input = buf.duplicate();

	EmbeddedChannel channel = new EmbeddedChannel(
	    new FixedLengthFrameDecoder(3));
	assertFalse(channel.writeInbound(input.readBytes(2)));
	assertTrue(channel.writeInbound(input.readBytes(7)));

	assertTrue(channel.finish());
	ByteBuf read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();
	read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();
	read = (ByteBuf) channel.readInbound();
	assertEquals(buf.readSlice(3), read);
	read.release();

	assertNull(channel.readInbound());
	buf.release();
    }
}

// 예제 9.4 AbsIntegerEncoder 테스트

public class AbsIntegerEncoderTest {
    @Test
    public void testEncoded() {
	ByteBuf buf = Unpooled.buffer();
	for (int i = 1; i < 10; i++) {
	    buf.writeInt(i * -1);
	}

	EmbaddedChannel channel = new EmbaddedChannel(
	    new AbsIntegerEncoder());
	assertTrue(channel.writeOutbound(buf));
	assertTrue(channel.finish());

	// 바이트를 읽음
	for (int i = 1; i < 10; i++) {
	    assertEquals(i, channel.readOutbound());
	}
	assertNull(channel.readOutbound());
    }
}

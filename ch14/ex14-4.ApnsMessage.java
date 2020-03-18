// 예제 14.4 ApnsMessage 구현

public final class ApnsMessage {
    private static final byte COMMAND = (byte) 1;
    public ByteBuf toBuffer() {
	short size = (short) (1 + // Command
	    4 + // Identifier
	    4 + // Expiry
	    2 + // Dt length header
	    32 + //Ds length
	    2 + // body length header
	    body.length);

	ByteBuf buf = Unpooled.buffer(size).order(ByteOrder.BIG_ENDIAN);
	buf.writeByte(COMMAND);
	buf.writeInt(identifier);
	buf.writeInt(expiryTime);
	buf.writeShort((short) deviceToken.length);
	buf.writeBytes(deviceToken);
	buf.writeShort((short) body.length);
	buf.writeBytes(body);
	return buf;
    }
}

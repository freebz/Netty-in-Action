// 예제 5.12 get()과 set() 메서드 이용

Charset utf8 = Charset.forName("UTF-8");
ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
System.out.println((char)buf.getByte(0));
int readerIndex = buf.readerIndex();
int writerIndex = buf.writerIndex();
buf.setByte(0, (byte)'B');
System.out.println((char)buf.getByte(0));
assert readerIndex == buf.readerIndex();
assert writerIndex == buf.writerIndex();

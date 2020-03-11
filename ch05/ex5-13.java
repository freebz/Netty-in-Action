// 예제 5.13 ByteBuf의 read()와 write() 메서드 이용

Charset utf8 = Charset.forName("UTF-8");
ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
System.out.println((char)buf.readByte());
int readerIndex = buf.readerIndex();
int writerIndex = buf.writerIndex();
buf.writeByte((byte)'?');
assert readerIndex == buf.readerIndex();
assert writerIndex != buf.writerIndex();

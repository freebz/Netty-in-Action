// 예제 5.11 ByteBuf의 복사본 만들기

Charset utf8 = Charset.forName("UTF-8");
ByteBuf buf = unpooled.copiedBuffer("Netty in Action rocks!", utf8);
ByteBuf copy = buf.copy(0, 14);
System.out.println(copy.toString(utf8));
buf.setByte(0, (byte)'J');
assert buf.getByte(0) != copy.getByte(0);

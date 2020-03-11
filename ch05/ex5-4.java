// 예제 5.4 CompositeByteBuf를 이용하는 복합 버퍼 패턴

CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
ByteBuf headerBuf = ...; // 보조 또는 다이렉트일 수 있음
ByteBuf bodyBuf = ...;   // 보조 또는 다이렉트일 수 있음
messageBuf.addComponents(headerBuf, bodyBuf);
.....
messageBuf.removeComponent(0); // 헤드를 제거
for (ByteBuf buf : messageBuf) {
    System.out.println(buf.toString());
}

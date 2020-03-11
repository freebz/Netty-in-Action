// 예제 5.7 모든 데이터 읽기

ByteBuf buffer = ...;
while (buffer.isReadable()) {
    System.out.println(buffer.readByte());
}

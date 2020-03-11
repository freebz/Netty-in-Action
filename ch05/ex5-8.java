// 예제 5.8 데이터 기록

// 버퍼의 기록할 수 있는 바이트를 임의의 정수로 채운다.
ByteBuf buffer = ...;
while (buffer.writableBytes() >= 4) {
    buffer.writeInt(random.nextInt());
}

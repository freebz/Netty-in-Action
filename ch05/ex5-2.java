// 예제 5.2 다이렉트 버퍼의 데이터 접근

ByteBuf directBuf = ...;
if (!directBuf.hasArray()) {
    int length = directBuf.readableBytes();
    byte[] array = new byte[length];
    directBuf.getBytes(directBuf.readerIndex(), array);
    handleArray(array, 0, length);
}

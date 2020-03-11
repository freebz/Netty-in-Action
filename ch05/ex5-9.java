// 예제 5.9 ByteBufProcessor를 이용해 \r을 잦는 방법

ByteBuf buffer = ...;
int index = buffer.forEachByte(ByteBufProcessor.FIND_CR);

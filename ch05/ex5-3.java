// 예제 5.3 ByteBuffer를 이용하는 복합 버퍼 패턴

// 배열을 이용해 메시지 부분을 저장
ByteBuffer[] message = new ByteBuffer[] { header, body };
// 새로운 ByteBuffer를 생성하고 헤더와 본문을 복사해 병합
ByteBuffer message2 =
    ByteBuffer.allocate(header.remaining() + body.remaining());
message2.put(header);
message2.put(body);
message2.flip();

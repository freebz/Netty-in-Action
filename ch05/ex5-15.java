// 예제 5.15 참조 카운팅

Channel channel = ...;
ByteBufAllocator allocator = channel.alloc();
...
ByteBuf buffer = allocator.directBuffer();
assert buffer.refCnt() == 1;
...

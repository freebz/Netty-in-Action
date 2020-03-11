// 예제 5.14 ByteBufAllocator 참조 얻기

Channel channel = ...;
ByteBufAllocator allocator = channel.alloc();
...
ChannelHandlerContext ctx = ...;
ByteBufAllocator allocator2 = ctx.alloc();
...

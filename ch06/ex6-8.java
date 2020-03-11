// 예제 6.8 ChannelHandlerContext의 write() 호출

ChannelHandlerContext ctx = ..;
ctx.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));

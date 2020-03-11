// 예제 6.6 ChannelHandlerContext에서 Channel을 얻음

ChannelHandlerContext ctx = ..;
Channel channel = ctx.channel();
channel.write(Unpooled.compiledBuffer("Netty in Action",
    CharsetUtil.UTF_8));

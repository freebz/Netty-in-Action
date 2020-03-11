// 예제 6.7 ChannelHandlerContext에서 ChannelPipeline을 얻음

ChannelHandlerContext ctx = ..;
ChannelPipeline pipeline = ctx.pipeline();
pipeline.write(Unpooled.copiedBuffer("Netty in Action",
    CharsetUtil.UTF_8));

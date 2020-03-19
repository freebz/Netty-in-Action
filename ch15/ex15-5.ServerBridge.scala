// 예제 15.5 네티와 피네이글의 브리지 연결

class ServerBridge[In, Out](
    serverTransport: Transport[In, Out] => Unit,
) extends SimpleChannelHandler {
    override def channelOpen(
        ctx: ChannelHandlerContext,
        e: ChannelStateEvent
    ){
        val channel = e.getChannel
        val transport = new ChannelTransport[In, Out](channel)
        serverTransport(transport)
        super.channelOpen(ctx, e)
    }
    override def exceptionCaught(
        ctx: ChannelHandlerContext,
        e: ExceptionEvent
    ) { // 예외를 로깅하고 채널을 닫음
    }
}

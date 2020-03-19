// 예제 15.3 네팁 기반 전송

case class Netty3Transporter[In, Out](
    pipelineFactory: ChannelPipelineFactory,
    newChannel: ChannelPipeline => Channel =
        Netty3Transporter.channelFactory.newChannel(_),
    newTransport: Channel => Transport[In, Out] =
        new ChannelTransport[In, Out](_),
    // 여러 시간 만료 및 SSL 옵션
) extends (
    (SocketAddress, StatsReceiver) => Future[Transport[In, Out]]
){
    private def newPipeline(
        addr: SocketAddress,
        statsReceiver: StatsReceiver
    )={
        val pipeline = pipelineFactory.getPipeline()
        // add stats, timeouts, and ssl handlers
        pipeline
    }
    private def newConfiguredChannel(
        addr: SocketAddress,
        statsReceiver: StatsReceiver
    )={
        val ch = newChannel(newPipeline(addr, statsReceiver))
        ch.getConfig.setOptions(channelOptions.asJava)
        ch
    }
    def apply(
        addr: SocketAddress,
        statsReceiver: StatsReceiver
    ): Future[Transport[In, Out]] = {
        val conn = new ChannelConnector[In, Out](
            () => newConfiguredChannel(addr, statsReceiver),
            newTransport, statsReceiver)
        conn(addr)
    }
}

// 예제 15.1 ChannelFactory 설정

object Netty3Transporter {
    val channelFactory: ChannelFactory =
        new NioClientSocketChannelFactory(
            Executor, 1 /*# 보스 스레드*/, WorkerPool, DefaultTimer
        ){
            // no-op; 해제할 수 없음
            override def releaseExternalResources() = ()
        }
    val defaultChannelOptions: Map[String, Object] = Map(
        "tcpNoDelay" -> java.lang.Boolean.TRUE,
        "resueAddress" -> java.lang.Boolean.TRUE
    )
}

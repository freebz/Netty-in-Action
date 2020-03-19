// 예제 15.2 원격 호스트 연결

private[netty3] class ChannelConnector[In, Out](
    newChannel: () => Channel,
    newTransport: Channel => Transport[In, Out]
) extends (SocketAddress => Future[Transport[In, Out]]) {
    def apply(addr: SocketAddress): Future[Transport[In, Out]] = {
        require(addr != null)
        val ch = try newChannel() catch {
            case NonFatal(exc) => return Future.exception(exc)
        }
        // 이제 전송이 채널에 바인딩됨
        // 이 과정은 메시지 손실을 예방하기 위해 연결 전에 수행됨
        val transport = newTransport(ch)
        val connectFuture = ch.connect(addr)
        val promise = new Promise[Transport[In, Out]]
        promise setInterruptHandler { case _cause =>
            // 취소를 네티 Future로 전파
            connectFuture.cancel()
        }
        connectFuture.addListener(new ChannelFutureListener {
            def operationComplete(f: ChannelFuture) {
                if (f.isSuccess) {
                    promise.setValue(transport)
                } else if (f.isCancelled) {
                    promise.setException(
                        WriteException(new CancelledConnectionException))
                } else {
                    promise.setException(WriteException(f.getCause))
                }
            }
        })
        promise onFailure { _ => Channels.close(ch)
        }
    }
}

// 예제 14.3 ChannelPipeline 설정

case class NamespaceTag(namespace: String)

class NamespaceBendwidthHandler extends ChannelDuplexHandler {
    private var rxBytes: Long = 0
    private var txBytes: Long = 0
    private var nsStats: Option[NamespaceStats] = None

    override def channelRead(ctx: ChannelHandlerContext, msg: Object) {
        msg match {
            case buf: ByteBuf => {
                rxBytes += buf.readableBytes()
                tryFlush(ctx)
            }
            case _ => { }
        }
        super.channelRead(ctx, msg)
    }
    override def write(ctx: ChannelHandlerContext, msg: Object,
            promise: ChannelPromise) {
        msg match {
            case buf: ByteBuf => {
                txBytes += buf.readableBytes()
                tryFlush(ctx)
                super.write(ctx, msg, promise)
            }
            case tag: NamespaceTag => {
                updateTag(tag.namespace, ctx)
            }
            case _ => {
                super.write(ctx, msg, promise)
            }
        }
    }
    private def tryFlush(ctx: ChannelHandlerContext) {
        nsStats match {
            case Some(stats: NamespaceStats) => {
                stats.logOutgoingBytes(txBytes.toInt)
                txBytes = 0
                stats.logIncomingBytes(rxBytes.toInt)
                rxBytes = 0
            }
            case None => {
                // no-op, 네임스페이스가 없음
            }
        }
    }
    private def updateTag(ns: String, ctx: ChannelHandlerContext) {
        val (_, isLocalNamespace) = NamespaceOwnershipManager.getOwner(ns)
        if (isLocalNamespace) {
            nsStats = NamespaceStatsListManager.get(ns)
            tryFlush(ctx)
        } else {
            //로컬이 아닌 네임스페이스, 바이트를 플러시함
            txBytes = 0
            rxBytes = 0
        }
    }
}

// 예제 11.12 ChunkStream을 이용한 파일 내용 전송

public class ChunkedWriteHandlerInitializer
    extends ChannelInitializer<Channel> {
    private final File file;
    private final SslContext sslCtx;

    public ChunkedWriteHandlerInitializer(File file, SslContext sslCtx) {
	this.file = file;
	this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(new SslHandler(sslCtx.createEngine()));
	pipeline.addLast(new ChunkedWriteHandler());
	pipeline.addLast(new WriteStreamHandler());
    }

    public final class WriteStreamHandler
	extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx)
	    throws Exception {
	    super.channelActive(ctx);
	    ctx.writeAndFlush(
		new ChunkedStream(new FileInputStream(file)));
	}
    }
}

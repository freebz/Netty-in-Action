// 예제 11.9 ChannelInitializer를 디코더 설치기로 이용

public class CmdHandlerInitializer extends ChannelInitializer<Channel> {
    final byte SPACE = (byte)' ';
    @Override
    protected void initChannel(Channel ch) throws Exception {
	ChannelPipeline pipeline = ch.pipeline();
	pipeline.addLast(new CmdDecoder(64 * 1024));
	pipeline.addLast(new CmdHandler());
    }

    public static final class Cmd {
	private final ByteBuf name;
	private final ByteBuf args;

	public Cmd(ByteBuf name, ByteBuf args) {
	    this.name = name;
	    this.args = args;
	}

	public ByteBuf name() {
	    return name;
	}

	public ByteBuf args() {
	    return args;
	}
    }

    public static final class CmdDecoder extends LineBasedFrameDecoder {
	public CmdDecoder(int maxLength) {
	    super(maxLength);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer)
	    throws Exception {
	    ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
	    if (frame == null) {
		return null;
	    }
	    int index = frame.indexOf(frame.readerIndex(),
	        frmae.writerIndex(), SPACE);
	    return new Cmd(frame.slice(frame.readerIndex(), index),
	        frame.slice(index + 1, frame.writerIndex()));
	}
    }

    public static final class CmdHandler
	extends SimpleChannelInboundHandler<Cmd> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Cmd msg)
	    throws Exception {
	    // 명령을 이용해 필요한 일을 함
	}
    }
}

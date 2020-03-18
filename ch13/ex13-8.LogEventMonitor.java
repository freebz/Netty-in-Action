// 예제 13.8 LogEventMonitor

public class LogEventMonitor {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogEventMonitor(InetSocketAddress address) {
	group = new NioEventLoopGroup();
	bootstrap = new Bootstrap();
	bootstrap.group(group)
	    .channel(NioDatagramChannel.class)
	    .option(ChannelOption.SO_BROADCAST, true)
	    .handler(new ChannelInitializer<Channel>() {
		@Override
		protected void initChannel(Channel channel)
		    throws Exception {
		    ChannelPipeline pipeline = channel.pipeline();
		    pipeline.addLast(new LogEventDecoder());
		    pipeline.addLast(new LogEventHandler());
		}
	    } )
	    .localAddress(address);
    }

    public Channel bind() {
	return bootstrap.bind().sync().channel();
    }

    public void stop() {
	group.shutdownGracefully();
    }

    public static void main(String[] main) throws Exception {
	if (args.length != 1) {
	    throw new IllegalArgumentException(
	    "Usage: LogEventMOnitor <port>");
	}
	LogEventMonitor monitor = new LogEventMonitor(
	    new InetSocketAddress(args[0]));
	try {
	    Channel channel = monitor.bind();
	    System.out.println("LogEventMonitor running");
	    channel.closeFuture().sync();
	} finally {
	    monitor.stop();
	}
    }
}

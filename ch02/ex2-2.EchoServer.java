// 예제 2.2 EchoServer 클래스

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
	this.port = port;
    }

    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	    System.err.println(
		"Usage: " + EchoServer.class.getSimpleName() +
		" <port>");
	}
	int port = Integer.parseInt(args[0]);
	new EchoServer(port).start();
    }

    public void start() throws Exception {
	final EchoServerHandler serverHandler = new EchoServerHandler();
	EventLoopGroup group = new NioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(group)
		.channel(NioServerSocketChannel.class)
		.localAddress(new InetSocketAddress(port))
		.childHandler(new ChannelInitializer<SocketChannel>() {
		    @Override
		    public void initChannel(SocketChannel ch)
			throws Exception {
		        ch.pipeline().addLast(serverHandler);
		    }
	        });
	    ChannelFuture f = b.bind().sync();
	    f.channel().closeFuture().sync();
	} finally {
	    group.shutdownGracefully().sync();
	}
    }
}

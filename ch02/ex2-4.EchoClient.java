// 예제 2.4 클라이언트의 주 클래스

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
	this.host = thos;
	this.port = port;
    }

    public void start() throws Exception {
	EventLoopGroup group = new NioEventLoopGroup();
	try {
	    Bootstrap b = new Bootstrap();
	    b.group(group)
		.channel(NioSocketChannel.class)
		.remoteAddress(new InetSocketAddress(host, port))
		.handler(new ChannelInitializer<SocketChannel>() {
		    @Override
		    public void initChannel(SocketChannel ch)
			throws Exception {
			ch.pipeline().addLast(
			    new EchoClientHandler());
		    }
	        });
	    ChannelFuture f = b.connect().sync();
	    f.channel().closeFuture().sync();
	} finally {
	    group.shutdownGracefully().sync();
	}
    }

    public static void main(String[] args) throws Exception {
	if (args.length != 2) {
	    System.err.println(
		"Usage: " + EchoClient.class.getSimpleName() +
		" <host> <port>");
	    return;
	}

	String host = args[0];
	int port = Integer.parseInt(args[1]);
	new EchoClient(host, port).start();
    }
}

// 예제 4.1 네티를 이용하지 않는 블로킹 네트워킹

public class PlainOioServer {
    public void serve(int port) throws IOException {
	final ServerSocket socket = new ServerSocket(port);
	try {
	    for (;;) {
		final Socket clientSocket = socket.accept();
		System.out.println(
		    "Accepted connection from " + clientSocket);
		new Thread(new Runnable()
		{
		    @Override
		    public void run() {
			OutputStream out;
			try {
			    out = clientSocket.getOutputStream();
			    out.write("Hi!\r\n".getBytes(
				Charset.forName("UTF-8")));
			    out.flush();
			    clientSocket.close();
			}
			catch (IOException e) {
			    e.printStackTrace();
			}
			finally {
			    try {
				clientSocket.close();
			    }
			    catch (IOException ex) {
				// 종료 시 무시함
			    }
			}
		    }
	        }).start();
	    }
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

// 예제 11.11 FileRegion을 이용한 파일 내용 전송

FileInputStream in = new FileInputStream(file);
fileRegion region = new DefaultFileRegion(
    in.getChannel(), 0, file.length());
channel.writeAndFlush(region).addListener(
    new ChannelFutureListener() {
	@Override
	public void operationComplete(ChannelFuture future)
	    throws Exception {
	    if (!future.isSuccess()) {
		Throwable cause = future.cause();
		// 필요한 작업을 함
	    }
	}
    });

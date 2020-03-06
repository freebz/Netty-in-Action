// 예제 4.6 Channel 하나를 여러 스레드에 이용

final Channel channel = ...
final ByteBuf buf = Unpooled.copiedBuffer("your data",
    CharsetUtil.UTF_8).retain();
Runnable writer = new Runnable() {
    @Override
    public void run() {
	channel.write(buf.duplicate());
    }
};
Executor executor = Executors.newCachedThreadPool();

// 한 스레드에서 기록
executor.execute(writer);

// 다른 스레드에서 기록
executer.execute(writer);
...

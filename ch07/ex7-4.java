// 예제 7.4 EventLoop를 이용한 반복 작업 예약

Channel ch = ...
ScheduleFuture<?> future = ch.eventLooop().scheduleAtFixedRate(
    new Runnable() {
	@Override
	public void run() {
	    System.out.println("Run every 60 seconds");
	}
    }, 60, 60, TimeUnit.Seconds);

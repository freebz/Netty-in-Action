// 예제 7.3 EventLoop를 이용한 작업 예약

Channel ch = ...
ScheduledFuture<?> future = ch.eventLoop().schedule(
    new Runnable() {
	@Override
	public void run() {
	    System.out.println("60 seconds later");
	}
    }, 60, TimeUnit.SECONDS);

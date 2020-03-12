// 예제 7.2 ScheduledExecutorService를 이용한 작업 예약

ScheduledExecutorService executor =
    Executors.newScheduledThreadPool(10);

ScheduledFuture<?> future = executor.schedule(
    new Runnable() {
	@Override
	public void run() {
	    System.out.println("60 seconds later");
	}
    }, 60, TimeUnit.SECONDS);
...
executor.shutdown();

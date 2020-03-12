// 예제 7.5 ScheduledFuture를 이용한 작업 취소

ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(...);
// 실행되는 다른 코드...
boolean mayInterruptIfRunning = false;
future.cancel(mayInterruptIfRunning);

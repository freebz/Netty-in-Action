// 예제 7.1 이벤트 루프에서 작업 실행

while (!terminated) {
    List<Runnable> readyEvents = blockUntilEventsReady();
    for (Runnable ev: readyEvents) {
	ev.run();
    }
}

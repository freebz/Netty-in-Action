// 예제 6.5 ChannelPipeline 수정

ChannelPipeline pipeline = ..;
FirstHandler firstHandler = new FirstHandler();
pipeline.addLast("handler1", firstHandler);
pipeline.addFirst("handler2", new SecondHandler());
pipeline.addLast("handler3", new ThirdHandler());
...
pipeline.remove("handle3");
pipeline.remove(firstHandler);
pipeline.replace("handler2", "handler4", new FourthHandler());

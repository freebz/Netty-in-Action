// 예제 14.1 ChannelPipeline 설정

pipelineFactory = new ChannelPipelineFactory() {
    @Override
    public ChannelPipeline getPipeline() throws Exception {
	ChannelPipeline pipeline = Channels.pipeline();
	pipeline.addLast("idleStateHandler", new IdleStateHandler(...));
	pipeline.addLast("httpServerCodec", new HttpServerCodec());
	pipeline.addLast("requestController",
	    new RequestController(...));
	return pipeline;
    }
};

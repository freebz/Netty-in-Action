// 예제 15.6 피네이글을 통한 서비스 구성

val timelineSvc = Thrift.newIface[TimelineService](...)
val tweetSvc = Thrift.newIface[TweetService](...)
val authSvc = Thrift.newIface[AuthService](...)

val authFilter = Filter.mk[Req, AuthReq, Res, Res] { (req, svc) =>
    authSvc.authenticate(req) flatMap svc(_)
}

val apiService = Service.mk[AuthReq, Res] { req =>
    timelineSvc(req.userId) flatMap {t1 =>
        val tweets = t1 map tweetSvc.getById(_)
        Future.collect(tweets) map tweetsToJson(_)
    }
}

Http.serve(":80", authFilter andThen apiService)

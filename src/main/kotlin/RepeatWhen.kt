import io.reactivex.Single
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
//    Single.just(throw Exception("Uoops"))

    Single.just("Tadam")
        .repeatWhen { it.delay(5, TimeUnit.SECONDS) }
        .onBackpressureLatest()
        .retry(3)
        .doOnComplete { println("onComplete") }
        .blockingSubscribe(
            {
                println("onNext: $it")
            },
            {
                println("onError: $it")
            },
            {
                println("onComplete")
            })
}
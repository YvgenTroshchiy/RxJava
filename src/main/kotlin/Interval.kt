import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {

    Observable.interval(0, 5, TimeUnit.SECONDS)
//            .applySchedulers(observationScheduler, observationScheduler)
//            .flatMap { getCurrentAndUpcomingRidesSingle().toObservable() }
        .retry(3)
}
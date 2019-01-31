import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.time.LocalTime
import java.util.concurrent.TimeUnit

private val trampolineScheduler = Schedulers.trampoline()

fun main(args: Array<String>) {

//    println(System.currentTimeMillis())
    println(LocalTime.now().second)

    val observable = Observable.interval(5, 5, TimeUnit.SECONDS)

    observable.subscribeOn(trampolineScheduler)
        .subscribe {
//            println(System.currentTimeMillis())
            println(LocalTime.now().second)
        }
}
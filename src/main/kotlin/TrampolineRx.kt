import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


private val onNext = Consumer<Int> { println("Number = $it") }

private val ioScheduler = Schedulers.io()
private val trampolineScheduler = Schedulers.trampoline()

fun main(args: Array<String>) {
    run(ioScheduler)
    println("---------------o")
    run(trampolineScheduler)
}

private fun run(scheduler: Scheduler) {
    Observable.just(1, 2, 3, 4, 5)
        .subscribeOn(scheduler)
        .subscribe(onNext)

    Observable.just(6, 7, 8, 9, 10)
        .subscribe(onNext)

}
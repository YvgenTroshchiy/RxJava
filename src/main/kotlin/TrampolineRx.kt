import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


val onNext = Consumer<Int> { println("Number = $it") }


fun main(args: Array<String>) {
//    io()
    trampoline()
}

private fun io() {
    Observable.just(1, 2, 3, 4, 5)
        .subscribeOn(Schedulers.io())
        .subscribe(onNext)

    Observable.just(6, 7, 8, 9, 10)
        .subscribe(onNext)
}

private fun trampoline() {
    Observable.just(1, 2, 3, 4, 5)
        .subscribeOn(Schedulers.trampoline())
        .subscribe(onNext)

    Observable.just(6, 7, 8, 9, 10)
        .subscribe(onNext)
}
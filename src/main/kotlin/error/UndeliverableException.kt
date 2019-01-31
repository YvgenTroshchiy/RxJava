package error

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

fun main(args: Array<String>) {
    zip()
}

fun zip() {
//    val observable1 = Observable.just(2)
//    val observable2 = Observable.just(5)

    val observable1 = Observable.error<Int>(Exception())
    val observable2 = Observable.error<Int>(Exception())

    val zipper = BiFunction<Int, Int, String> { one, two -> "$one + $two" }

    observable1.zipWith(observable2, zipper)
//        .onErrorReturnItem("Error")
        .subscribe(
            { println(it) },
            { it.printStackTrace() }
        )
}
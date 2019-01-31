package error

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

fun main(args: Array<String>) {
    case1()
}

fun case1() {

    val observable1 = Observable.error<Int>(Exception())
    val observable2 = Observable.error<Int>(Exception())

    val zipper = BiFunction<Int, Int, String> { one, two -> "$one - $two" }

    observable1.zipWith(observable2, zipper)
        .subscribe(
            { println(it) },
            { it.printStackTrace() }
        )
}

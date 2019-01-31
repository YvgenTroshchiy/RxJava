import io.reactivex.Observable
import io.reactivex.exceptions.Exceptions
import java.io.IOException

fun main(args: Array<String>) {

//    runtimeException()

    tryException()
}

private fun runtimeException() {
    Observable.just("Hello!")
        .map { throw RuntimeException() }
        .subscribe(
            { println("it: $it") },
            { println("Error! : $it") }
        )
}

private fun tryException() {
    fun foo(s: String): String {
        throw IOException()
    }

    Observable.just("Hello!")
        .map {
            return@map try {
                foo(it)
            } catch (t: Throwable) {
                throw Exceptions.propagate(t)
            }
        }
        .subscribe(
            { println("it: $it") },
            { println("Error! : $it") }
        )
}

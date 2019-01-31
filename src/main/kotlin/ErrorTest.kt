import io.reactivex.Observable
import io.reactivex.exceptions.Exceptions

fun main(args: Array<String>) {
//    runtimeException()
//    tryException()

    observableError()
}

private fun runtimeException() {
    Observable.just("Hello!")
        .map { throw RuntimeException() }
        .subscribe(
            { println("it: $it") },
            { println("Error: $it") }
        )
}

private fun tryException() {
    fun transform(s: String): String {
        throw IllegalArgumentException()
    }

    Observable.just("Hello!")
        .map {
            return@map try {
                transform(it)
            } catch (t: Throwable) {
                throw Exceptions.propagate(t)
            }
        }
        .subscribe(
            { println("it: $it") },
            { println("Error: $it") }
        )
}

private fun observableError() {
    fun transform(s: String): String {
        throw Throwable("Some error")
    }

    Observable.just("Hello!")
        .flatMap {
            return@flatMap try {
                Observable.just(transform(it))
            } catch (t: Throwable) {
                Observable.error<Throwable>(t)
            }
        }
        .subscribe(
            { println("it: $it") },
            { println("Error: $it") }
        )
}

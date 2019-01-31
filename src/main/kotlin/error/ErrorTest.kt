package error

import io.reactivex.Observable
import io.reactivex.exceptions.Exceptions
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
//    error.runtimeException()
//    error.tryException()
//    error.observableError()

//    error.onExceptionResumeNext()
//    error.onErrorReturnItem()
    onErrorReturn()
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
    fun transform(s: String): String = throw IllegalArgumentException()

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
    fun transform(s: String): String = throw Throwable("Some error")

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

fun onExceptionResumeNext() {
    Observable.fromArray(1, 2, 3)
        .doOnNext {
            if (it == 2) throw (RuntimeException("Exception on 2"))
        }
        .doOnError {
            println("Doing on error")
        }
        .onExceptionResumeNext(Observable.just(-1))
        .subscribeBy(
            onNext = {
                println(it)
            }, onError = {
                println(it.message)
            }, onComplete = {
                println("Complete")
            }
        )
}

fun onErrorReturnItem() {
    Observable.fromArray(1, 2, 3)
        .doOnNext {
            if (it == 2) throw (RuntimeException("Exception on 2"))
        }
        .doOnError {
            println("Doing on error")
        }
        .onErrorReturnItem(-1)
        .subscribeBy(
            onNext = {
                println(it)
            }, onError = {
                println(it.message)
            }, onComplete = {
                println("Complete")
            }
        )
}

fun onErrorReturn() {
    Observable.fromArray(1, 2, 3)
        .doOnNext {
            if (it == 2) {
                throw (RuntimeException("Exception on 2"))
            }
        }
        .onErrorReturn {
            if (it.message == "<something you want>") {
                1 // Return a value based on error type
            } else {
                100 // Return another value based on different error type
            }
        }
        .subscribeBy(
            onNext = {
                println(it)
            }, onError = {
                println(it.message)
            }, onComplete = {
                println("Complete")
            }
        )
}
import io.reactivex.Observable

fun main(args: Array<String>) {

    Observable.just("Hello!")
        .map { throw RuntimeException() }
        .subscribe(
            { System.out.println() },
            { println("Error! : $it") }
        )
}
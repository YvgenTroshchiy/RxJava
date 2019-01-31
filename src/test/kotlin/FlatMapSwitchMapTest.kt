import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class FlatMapSwitchMapTest {

    private val scheduler = TestScheduler()

    @Test fun `test has item`() {

        // given
        val actualOutput = ArrayList<String>()
        val keywordToSearch = Arrays.asList("b", "bo", "boo", "book", "books")

        // when
        Observable.fromIterable(keywordToSearch)
            .flatMap {
                Observable
                    .just("$it FirstResult", "$it SecondResult")
                    .delay(10, TimeUnit.SECONDS, scheduler)
            }
            .toList()
            .doOnSuccess { actualOutput.addAll(it) }
            .subscribe({
                println(it)
            }, {
                println("error: $it")
            })

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)

        // then
        assertThat<List<String>>(
            actualOutput,
            hasItems(
                "b FirstResult", "b SecondResult",
                "boo FirstResult", "boo SecondResult",
                "bo FirstResult", "bo SecondResult",
                "book FirstResult", "book SecondResult",
                "books FirstResult", "books SecondResult"
            )
        )
    }

    @Test fun `test flat map`() {

        // given
        val actualOutput = ArrayList<String>()
        val keywordToSearch = Arrays.asList("b", "bo", "boo", "book", "books")

        // when
        Observable.fromIterable(keywordToSearch)
            .switchMap {
                Observable
                    .just("$it FirstResult", "$it SecondResult")
                    .delay(10, TimeUnit.SECONDS, scheduler)
            }
            .toList()
            .doOnSuccess { actualOutput.addAll(it) }
            .subscribe({
                println(it)
            }, {
                println("error: $it")
            })

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)

        // then
        assertEquals(2, actualOutput.size)
        assertThat(actualOutput, hasItems("books FirstResult", "books SecondResult"))
    }
}
package poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {

    private lateinit var deck: Deck

    @BeforeEach
    fun setup() {
        deck = Deck()
    }

    @Test
    fun testAllCardsAreUnique() {
        val seenCards = mutableListOf<Card>()
        while (!deck.isEmpty()) {
            val card = deck.drawCard()
            assertFalse(seenCards.contains(card))
        }
    }

    @Test
    fun testDeckIsEmptyAfter52Cards() {
        var count = 0
        while (!deck.isEmpty()) {
            deck.drawCard()
            count++
        }
        assertEquals(52, count)
    }

    @Test
    fun testCannotDrawMoreThan52Cards() {
        var count = 0
        while (deck.drawCard() != null) {
            count++
        }
        assertEquals(52, count)
    }
}
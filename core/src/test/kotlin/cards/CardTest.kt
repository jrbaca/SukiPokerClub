package cards

import cards.Card
import cards.Rank
import cards.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun testIdenticalCardsAreEqual() {
        val card1 = Card(Rank.EIGHT, Suit.SPADE)
        val card2 = Card(Rank.EIGHT, Suit.SPADE)
        assertEquals(card1, card2)
    }

    @Test
    fun testDifferentCardsAreNotEqual() {
        val card1 = Card(Rank.EIGHT, Suit.SPADE)
        val card2 = Card(Rank.KING, Suit.SPADE)
        val card3 = Card(Rank.EIGHT, Suit.HEART)
        val card4 = Card(Rank.TWO, Suit.DIAMOND)

        assertNotEquals(card1, card2)
        assertNotEquals(card1, card3)
        assertNotEquals(card1, card4)
    }
}
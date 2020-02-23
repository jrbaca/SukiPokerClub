package cards

class Deck {
    private val cardQueue: MutableList<Card> = getAllCardsInRandomOrder()

    /**
     * Removes the card at the top of the deck and returns it. Returns null if no cards left.
     */
    fun drawCard(): Card? {
        return if (!isEmpty()) {
            cardQueue.removeAt(0)
        } else {
            null
        }
    }

    /**
     * Moves a card to the nth position in the deck. 0 = first, 1 = second, and so on.
     * @throws DeckEmptyException
     */
    fun moveCardToNthPosition(card: Card, position: Int) {

        if (cardQueue.contains(card)) {
            TODO()
        } else {
            throw DeckEmptyException()
        }
    }

    fun isEmpty(): Boolean {
        return cardQueue.isEmpty()
    }

    companion object {

        private fun getAllCardsInRandomOrder(): MutableList<Card> {
            val cards = mutableListOf<Card>()

            val allCards = mutableSetOf<Card>()
            for (rank in Rank.values()) {
                for (suit in Suit.values()) {
                    allCards.add(Card(rank, suit))
                }
            }

            while (allCards.isNotEmpty()) {
                val card = allCards.random()
                allCards.remove(card)
                cards.add(card)
            }

            return cards
        }
    }
}
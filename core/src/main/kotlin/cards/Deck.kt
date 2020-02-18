package cards

class Deck {
    private val cards = getRandomCards()

    fun drawCard(): Card? {
        return if (!isEmpty()) {
            cards.removeAt(0)
        } else {
            null
        }
    }

    fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    companion object {
        private fun getRandomCards(): MutableList<Card> {
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
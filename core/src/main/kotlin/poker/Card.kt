package poker

data class Card(
    val rank: Rank,
    val suit: Suit
)

enum class Suit {
    SPADE,
    CLUB,
    HEART,
    DIAMOND
}

enum class Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}
package cards

class DeckEmptyException: Throwable {
    constructor() : super()
    constructor(message: String) : super(message)
}
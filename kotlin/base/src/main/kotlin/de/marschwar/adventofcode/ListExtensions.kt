fun <T : Any> List<T>.head() = this.first()

fun <T : Any> List<T>.tail() = this.takeLast(size - 1)

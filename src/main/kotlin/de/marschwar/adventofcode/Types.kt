package de.marschwar.adventofcode

typealias PuzzleInput = Sequence<String>
typealias Point = Pair<Int, Int>

class Stack<T>(items: Array<T>) {
    val items: MutableList<T> = items.toMutableList()

    fun push(item: T) {
        items.add(0, item)
    }

    fun pop(): T {
        check(items.isNotEmpty())
        return items.removeAt(0)
    }
}
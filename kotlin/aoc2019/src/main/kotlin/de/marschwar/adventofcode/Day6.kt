package de.marschwar.adventofcode

class Day6 : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val graph = input
            .toOrbitInfoSequence()
            .toGraph("COM")

        graph.visit { planet, parents -> planet.orbitingPlanets = parents }
        return graph.sumBy { it.orbitingPlanets.count() }
    }

    override fun part2(input: PuzzleInput): Any {
        val graph = input
            .toOrbitInfoSequence()
            .toGraph("COM")
        graph.visit { planet, parents -> planet.orbitingPlanets = parents }

        val san = graph.first { it.name == "SAN" }
        val you = graph.first { it.name == "YOU" }

        val sanPath = san.orbitingPlanets
        val youPath = you.orbitingPlanets

        val orbitIntersect = sanPath.intersect(youPath).last()
        val distanceToSan = sanPath.size - sanPath.indexOf(orbitIntersect) - 1
        val distanceToYou = youPath.size - youPath.indexOf(orbitIntersect) - 1
        return distanceToSan + distanceToYou
    }

    private fun PuzzleInput.toOrbitInfoSequence(): Sequence<OrbitInfo> =
        map { it.split(")") }
            .map { OrbitInfo(it.first(), it.last()) }

    private fun Sequence<OrbitInfo>.toGraph(rootName: String): Planet {
        var root = Planet(rootName)
        val knownPlanetNames = mutableSetOf(rootName)
        var unassignedInfos = toSet()
        while (unassignedInfos.isNotEmpty()) {
            unassignedInfos.forEach { info ->
                if (info.planetName in knownPlanetNames) {
                    root = root.applyInfo(info)
                    knownPlanetNames.add(info.satelliteName)
                    unassignedInfos = unassignedInfos - info
                }
            }
        }
        return root
    }
}

private data class OrbitInfo(val planetName: String, val satelliteName: String)
private data class Planet(
    val name: String,
    val satellites: List<Planet> = emptyList()
) {
    var orbitingPlanets: List<Planet> = emptyList()

    fun visit(parents: List<Planet> = emptyList(), visitor: (Planet, List<Planet>) -> Unit) {
        visitor(this, parents)
        satellites.forEach { it.visit(parents + this, visitor) }
    }

    fun sumBy(function: (Planet) -> Int): Int {
        return function.invoke(this) + satellites.sumBy { it.sumBy(function) }
    }

    fun filter(function: (Planet) -> Boolean): Iterable<Planet> {
        val matches = if (function.invoke(this)) listOf(this) else emptyList()
        return matches + satellites.flatMap { it.filter(function) }
    }

    fun first(function: (Planet) -> Boolean): Planet {
        return filter(function).first()
    }

    fun applyInfo(info: OrbitInfo): Planet {
        if (info.planetName == name) {
            return copy(satellites = satellites + Planet(info.satelliteName))
        }
        if (satellites.isEmpty()) return this

        return copy(satellites = satellites.map { it.applyInfo(info) })
    }

    override fun toString(): String {
        return if (satellites.isEmpty()) name else "$name->$satellites"
    }

}

fun main() = Day6().solve()

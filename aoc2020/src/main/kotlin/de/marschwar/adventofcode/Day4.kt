package de.marschwar.adventofcode

class Day4 : Puzzle(removeEmptyLines = false) {

    override fun part1(input: Sequence<String>): Any {
        return parseInput(input).count { it.valid1 }
    }

    override fun part2(input: Sequence<String>): Any {
        return parseInput(input).count { it.valid2 }
    }

    private fun parseInput(input: Sequence<String>): List<Passport> {
        val passports = mutableListOf<Passport>()
        val iterator = input.iterator()
        var current: Passport? = null
        while (iterator.hasNext()) {
            val line = iterator.next()
            if (line.isEmpty()) {
                if (current != null) {
                    passports.add(current)
                    current = null
                }
                continue
            }
            if (current == null) {
                current = Passport()
            }
            line.split(" ").forEach { pair ->
                val (key, value) = pair.split(":")
                current = current?.withData(key, value)
            }
        }
        current?.let { passports.add(it) }
        return passports
    }

    private enum class EyeColor {
        amb, blu, brn, gry, grn, hzl, oth
    }

    private data class Height(private val amount: Int, private val metric: Boolean) {
        val valid: Boolean
            get() = if (metric) amount in (150..193) else amount in (59..76)

        companion object {
            fun of(input: String): Height? {
                if (input.length < 3) {
                    return null
                }
                val amount = input.substring(0, input.length - 2).toInt()
                return when (input.substring(input.length - 2)) {
                    "cm" -> Height(amount, true)
                    "in" -> Height(amount, false)
                    else -> null
                }
            }
        }
    }

    private data class Passport(
        private val birth: String? = null,
        private val issue: String? = null,
        private val expire: String? = null,
        private val height: Height? = null,
        private val hair: String? = null,
        private val eye: EyeColor? = null,
        private val pid: String? = null,
        private val cid: String? = null,
    ) {
        val valid1: Boolean = noneNull(birth, issue, expire, height, hair, eye, pid)

        val valid2: Boolean = birth.inRange(1920..2002)
                && issue.inRange(2010..2020)
                && expire.inRange(2020..2030)
                && height != null && height.valid
                && hair != null && hair.matches("""#[\da-f]{6}""".toRegex())
                && eye != null
                && pid != null && pid.matches("""\d{9}""".toRegex())

        private fun noneNull(vararg values: Any?) = values.all { it != null }

        fun withData(key: String, value: String): Passport {
            return when (key) {
                "byr" -> copy(birth = value)
                "iyr" -> copy(issue = value)
                "eyr" -> copy(expire = value)
                "hgt" -> copy(height = Height.of(value))
                "hcl" -> copy(hair = value)
                "ecl" -> copy(eye = EyeColor.values().find { it.name == value })
                "pid" -> copy(pid = value)
                "cid" -> copy(cid = value)
                else -> error("unexpected key")
            }
        }

        private fun String?.inRange(range: IntRange) = this?.toIntOrNull()?.let { it in range } ?: false
    }
}

fun main() = Day4().solve()

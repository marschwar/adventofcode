package de.marschwar.adventofcode

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class Day4Test {

    @ParameterizedTest
    @ValueSource(strings = ["111111", "112233", "111234", "122234"])
    fun validPasswordsPart1(string: String) {
        isValidPasswordPart1(string) shouldBe true
    }

    @ParameterizedTest
    @ValueSource(strings = ["223450", "123789", "616928"])
    fun invalidPasswordsPart1(string: String) {
        isValidPasswordPart1(string) shouldBe false
    }

    @ParameterizedTest
    @ValueSource(strings = ["112233", "111122"])
    fun validPasswordsPart2(string: String) {
        isValidPasswordPart2(string) shouldBe true
    }

    @ParameterizedTest
    @ValueSource(strings = ["223450", "123789", "616928", "111111", "111234", "122234", "123444"])
    fun invalidPasswordsPart2(string: String) {
        isValidPasswordPart2(string) shouldBe false
    }
}
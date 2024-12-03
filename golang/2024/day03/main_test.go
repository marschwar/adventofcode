package day03

import "testing"

func TestSolvePart1(t *testing.T) {
	input := "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
	actual := SolvePart1(input)

	if actual != 161 {
		t.Error(actual)
	}
}
func TestSolvePart2(t *testing.T) {
	input := "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
	actual := SolvePart2(input)

	if actual != 48 {
		t.Error(actual)
	}
}

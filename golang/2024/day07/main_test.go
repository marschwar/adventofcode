package day07

import "testing"

var input = `
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20`

func TestSolvePart1(t *testing.T) {
	actual := SolvePart1(input)

	if actual != 3749 {
		t.Error(actual)
	}
}
func TestSolvePart2(t *testing.T) {
	actual := SolvePart2(input)

	if actual != 6 {
		t.Error(actual)
	}
}

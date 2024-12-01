package day01

import "testing"

var input = `
3   4
4   3
2   5
1   3
3   9
3   3`

func TestSolvePart1(t *testing.T) {
	actual := SolvePart1(input)

	if actual != 11 {
		t.Errorf("expected: %s\nactual: %d", "11", actual)
	}
}
func TestSolvePart2(t *testing.T) {
	actual := SolvePart2(input)

	if actual != 31 {
		t.Errorf("expected: %s\nactual: %d", "31", actual)
	}
}

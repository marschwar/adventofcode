package day02

import "testing"

var input = `
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
`

func TestSolvePart1(t *testing.T) {
	actual := SolvePart1(input)

	if actual != 2 {
		t.Error(actual)
	}
}
func TestSolvePart2(t *testing.T) {
	actual := SolvePart2(input)

	if actual != 4 {
		t.Error(actual)
	}
}
func TestSolvePart2EdgeCases(t *testing.T) {
	input := `
48 46 47 49 51 54 56
1 1 2 3 4 5
1 2 3 4 5 5
5 1 2 3 4 5
1 4 3 2 1
1 6 7 8 9
1 2 3 4 3
9 8 7 6 7
7 10 8 10 11
29 28 27 25 26 25 22 20`

	actual := SolvePart2(input)

	if actual != 10 {
		t.Error(actual)
	}
}

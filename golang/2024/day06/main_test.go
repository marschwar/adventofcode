package day06

import "testing"

var input = `
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...`

func TestSolvePart1(t *testing.T) {
	actual := SolvePart1(input)

	if actual != 41 {
		t.Error(actual)
	}
}
func TestSolvePart2(t *testing.T) {
	actual := SolvePart2(input)

	if actual != 6 {
		t.Error(actual)
	}
}

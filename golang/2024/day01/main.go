package day01

import (
	"regexp"
	"sort"
	"strconv"
	"strings"
)

var lineSplitter = regexp.MustCompile(`\d+`)

func SolvePart1(input string) int {
	first, second := extractCols(input)

	sort.Ints(first)
	sort.Ints(second)

	sum := 0
	for i := 0; i < len(first); i++ {
		sum += absDiff(first[i], second[i])
	}
	return sum
}

func SolvePart2(input string) int {
	first, second := extractCols(input)

	sort.Ints(first)
	sort.Ints(second)

	occurrences := make(map[int]int)

	for _, number := range first {
		_, exists := occurrences[number]
		if !exists {
			occ := 0
			for _, other := range second {
				if other == number {
					occ++
				}
			}
			occurrences[number] = occ
		}
	}

	result := 0
	for _, number := range first {
		occ, exists := occurrences[number]
		if exists {
			result += number * occ
		}
	}
	return result
}

func extractCols(input string) ([]int, []int) {
	lines := strings.Split(strings.Trim(input, "\n"), "\n")
	first := make([]int, len(lines))
	second := make([]int, len(lines))

	for i, line := range lines {
		left, right := lineToPair(line)
		first[i] = left
		second[i] = right
	}
	return first, second
}

func lineToPair(line string) (int, int) {
	elems := lineSplitter.FindAllString(line, 2)
	left, _ := strconv.Atoi(strings.Trim(elems[0], " "))
	right, _ := strconv.Atoi(strings.Trim(elems[1], " "))
	return left, right
}

func absDiff(a, b int) int {
	if a > b {
		return a - b
	}
	return b - a
}

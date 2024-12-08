package day07

import (
	"regexp"
	"strconv"
	"strings"
)

type candidate struct {
	result int
	parts  []int
}

func SolvePart1(input string) int {
	candidates := parseInput(input)
	result := 0
	for _, candidate := range candidates {
		if isCorrect1(candidate.result, candidate.parts[0], candidate.parts[1:]) {
			result += candidate.result
		}
	}

	return result
}

func SolvePart2(_ string) int {

	return 0
}

func parseInput(input string) []candidate {
	lines := strings.Split(strings.Trim(input, "\n"), "\n")
	candidates := make([]candidate, len(lines))
	pattern := regexp.MustCompile(`\d+`)

	for i, line := range lines {
		matches := pattern.FindAllString(line, -1)
		result, _ := strconv.Atoi(matches[0])
		candidates[i] = candidate{
			result, asI(matches[1:]),
		}
	}
	return candidates
}

func isCorrect1(expected int, current int, parts []int) bool {
	if len(parts) == 0 {
		return expected == current
	}
	if current > expected {
		return false
	}
	if isCorrect1(expected, current+parts[0], parts[1:]) {
		return true
	}
	return isCorrect1(expected, current*parts[0], parts[1:])
}

func asI(input []string) []int {
	result := make([]int, len(input))
	for i, s := range input {
		result[i], _ = strconv.Atoi(s)
	}
	return result
}

package day02

import (
	"regexp"
	"slices"
	"strconv"
	"strings"
)

var lineSplitter = regexp.MustCompile(`\d+`)

func SolvePart1(input string) int {
	data := parseInput(input)
	result := 0

	for _, row := range data {
		if isSafe(row) {
			result += 1
		}
	}
	return result
}

func SolvePart2(input string) int {
	data := parseInput(input)
	result := 0

	for _, row := range data {
		if isSafe2(row) {
			result++
		}
	}
	return result
}

func isSafe(elems []int) bool {
	prev := elems[0]
	delta := 0
	for i := 1; i < len(elems); i++ {
		current := elems[i]
		currentDelta := current - prev
		if isInvalid(currentDelta, delta) {
			return false
		}
		delta = currentDelta
		prev = current
	}
	return true
}

func isSafe2(elems []int) bool {
	if isSafe(elems) {
		return true
	}
	// remove one element at the time and check if it becomes valid
	for i := 0; i < len(elems); i++ {
		slice := slices.Clone(elems)
		slice = append(slice[:i], slice[i+1:]...)
		if isSafe(slice) {
			return true
		}
	}
	return false
}

func isInvalid(currentDelta int, delta int) bool {
	if abs(currentDelta) < 1 || abs(currentDelta) > 3 {
		return true
	}
	if delta < 0 && (currentDelta < -3 || currentDelta > -1) {
		return true
	}
	if delta > 0 && (currentDelta < 1 || currentDelta > 3) {
		return true
	}
	return false
}

func parseInput(input string) [][]int {
	lines := strings.Split(strings.Trim(input, "\n"), "\n")
	rows := make([][]int, len(lines))
	for i, line := range lines {
		parts := lineSplitter.FindAllString(line, -1)
		row := make([]int, len(parts))
		for j, part := range parts {
			row[j], _ = strconv.Atoi(part)
		}
		rows[i] = row
	}
	return rows
}

func abs(a int) int {
	if a < 0 {
		return a * -1
	}
	return a
}

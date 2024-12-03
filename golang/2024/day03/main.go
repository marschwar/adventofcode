package day03

import (
	"regexp"
	"strconv"
)

func SolvePart1(input string) int {
	pattern := regexp.MustCompile(`mul\((\d{1,3}),(\d{1,3})\)`)
	matches := pattern.FindAllStringSubmatch(input, -1)
	result := 0
	for _, match := range matches {
		left, _ := strconv.Atoi(match[1])
		right, _ := strconv.Atoi(match[2])
		result += left * right
	}

	return result
}

func SolvePart2(input string) int {
	pattern := regexp.MustCompile(`do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)`)
	matches := pattern.FindAllStringSubmatch(input, -1)
	result := 0
	active := true
	for _, match := range matches {
		fullMatch := match[0]
		switch fullMatch {
		case "do()":
			{
				active = true
			}

		case "don't()":
			{
				active = false
			}
		default:
			{
				if active {
					left, _ := strconv.Atoi(match[1])
					right, _ := strconv.Atoi(match[2])
					result += left * right

				}
			}
		}
	}
	return result
}

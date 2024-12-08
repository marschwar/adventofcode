package day06

import (
	"strings"
)

type point struct {
	x int
	y int
}
type location struct {
	blocked         bool
	visitDirections []int
}

func SolvePart1(input string) int {
	dungeon, currentPosition := parseInput(input)
	return solve(dungeon, currentPosition)
}

func SolvePart2(input string) int {

	dungeon, startPosition := parseInput(input)
	result := 0
	for i, locations := range dungeon {
		for j, loc := range locations {
			if !loc.blocked || i != startPosition.y || j != startPosition.y {
				clone := clone(dungeon)
				clone[i][j].blocked = true
				solution := solve(clone, startPosition)
				if solution == -1 {
					result++
				}
			}
		}
	}
	return result
}

func solve(dungeon [][]location, startPosition point) int {
	currentDirection := 0
	currentPosition := startPosition

	for {
		next := step(currentPosition, currentDirection)
		if next.x < 0 || next.x >= len(dungeon[0]) || next.y < 0 || next.y >= len(dungeon) {
			return countVisited(dungeon)
		}
		if dungeon[next.y][next.x].blocked {
			currentDirection = turn(currentDirection)
		} else {
			for _, dir := range dungeon[next.y][next.x].visitDirections {
				if dir == currentDirection {
					return -1
				}
			}
			dungeon[next.y][next.x].visitDirections = append(dungeon[next.y][next.x].visitDirections, currentDirection)
			currentPosition = next
		}
	}

}

func step(current point, direction int) point {
	switch direction {
	case 0:
		return point{current.x, current.y - 1}
	case 1:
		return point{current.x + 1, current.y}
	case 2:
		return point{current.x, current.y + 1}
	default:
		return point{current.x - 1, current.y}
	}
}

func turn(direction int) int {
	return (direction + 1) % 4

}

func countVisited(dungeon [][]location) int {
	visited := 0
	for _, locsInRow := range dungeon {
		for _, loc := range locsInRow {
			if len(loc.visitDirections) > 0 {
				visited++
			}
		}
	}
	return visited
}

func parseInput(input string) ([][]location, point) {
	start := point{0, 0}
	lines := strings.Split(strings.Trim(input, "\n"), "\n")
	dungeon := make([][]location, len(lines))

	for row, line := range lines {
		dungeon[row] = make([]location, len(line))
		for col, char := range line {
			blocked := char == '#'
			dungeon[row][col] = location{blocked: blocked, visitDirections: make([]int, 0)}
			if char == '^' {
				start = point{x: col, y: row}
			}
		}
	}

	return dungeon, start
}

func clone(original [][]location) [][]location {
	clone := make([][]location, len(original))
	for i, row := range original {
		clone[i] = make([]location, len(row))
		for j, loc := range row {
			clone[i][j] = loc
		}
	}
	return clone
}

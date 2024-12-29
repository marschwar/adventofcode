package day14

import (
	"regexp"
	"strconv"
	"strings"
)

const seconds = 100

type point struct {
	x, y int
}

type robot struct {
	position point
	velocity point
}

func SolvePart1(input string, config ...int) int {
	gridSize := point{
		x: 101,
		y: 103,
	}
	if len(config) == 2 {
		gridSize.x = config[0]
		gridSize.y = config[1]
	}

	robots := parseInput(input)
	var quadrants [4]int

	for _, robot := range robots {
		r := robot
		for i := 0; i < seconds; i++ {
			r = step(r, gridSize)
		}
		if r.position.y < gridSize.y/2 {
			if r.position.x < gridSize.x/2 {
				quadrants[0]++
			} else if r.position.x > gridSize.x/2 {
				quadrants[1]++
			}
		} else if r.position.y > gridSize.y/2 {
			if r.position.x < gridSize.x/2 {
				quadrants[2]++
			} else if r.position.x > gridSize.x/2 {
				quadrants[3]++
			}
		}
	}
	return quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3]
}

func SolvePart2(input string) int {

	gridSize := point{
		x: 101,
		y: 103,
	}

	robots := parseInput(input)

	tick := 1

	for {
		for i, robot := range robots {
			robots[i] = step(robot, gridSize)
		}
		if couldBeTree(robots, gridSize) {
			for row := 0; row < gridSize.y; row++ {
				printTreeLine(row, robots, gridSize)
			}
			println("\n\n\n")
			return tick
		}
		tick++
		if tick%1000 == 0 {
			println(tick)
		}
		if tick >= 10000000 {
			return -1
		}
	}

}

func printTreeLine(row int, robots []robot, size point) {
	for col := 0; col < size.x; col++ {
		if hasRobotAt(robots, point{col, row}) {
			print("#")
		} else {
			print(".")
		}
	}
	print("\n")
}

func couldBeTree(robots []robot, size point) bool {
	samples := 20
	misses := 0
	for i := 0; i < samples; i++ {
		robot := robots[i]
		if !hasRobotAt(robots, point{size.x - robot.position.x, robot.position.y}) {
			misses++
		}
		if misses > samples/2 {
			return false
		}
	}
	return true
}

func hasRobotAt(robots []robot, target point) bool {
	for _, robot := range robots {
		if robot.position.x == target.x && robot.position.y == target.y {
			return true
		}
	}
	return false
}

func parseInput(input string) []robot {
	pattern := regexp.MustCompile(`p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)`)
	lines := strings.Split(strings.Trim(input, "\n"), "\n")
	robots := make([]robot, len(lines))

	for i, line := range lines {
		match := pattern.FindAllStringSubmatch(line, -1)[0]
		px, _ := strconv.Atoi(match[1])
		py, _ := strconv.Atoi(match[2])
		vx, _ := strconv.Atoi(match[3])
		vy, _ := strconv.Atoi(match[4])
		robots[i] = robot{
			position: point{
				x: px,
				y: py,
			},
			velocity: point{
				x: vx,
				y: vy,
			},
		}
	}
	return robots
}

func step(robot robot, gridSize point) robot {
	x := next(robot.position.x, robot.velocity.x, gridSize.x)
	y := next(robot.position.y, robot.velocity.y, gridSize.y)
	robot.position = point{x: x, y: y}
	return robot
}

func next(current, step, max int) int {
	next := (current + step) % max
	if next < 0 {
		next = max + next
	}
	return next
}

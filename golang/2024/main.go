package main

import (
	day1 "2024/day01"
	"fmt"
	"os"
	"path/filepath"
	"runtime"
)

func main() {

	_, sourceFile, _, _ := runtime.Caller(0)
	sourceDir := filepath.Dir(sourceFile)
	filePath := filepath.Join(sourceDir, "day01/input.txt")

	data, _ := os.ReadFile(filePath)
	input := string(data)
	fmt.Printf("Part 1: %d", day1.SolvePart1(input))
	fmt.Printf("Part 2: %d", day1.SolvePart2(input))
}

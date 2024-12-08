package main

import (
	current "2024/day06"
	"fmt"
	"os"
	"path/filepath"
	"runtime"
)

func main() {

	_, sourceFile, _, _ := runtime.Caller(0)
	sourceDir := filepath.Dir(sourceFile)
	filePath := filepath.Join(sourceDir, "day06/input.txt")

	data, _ := os.ReadFile(filePath)
	input := string(data)
	fmt.Printf("Part 1: %d\n", current.SolvePart1(input))
	fmt.Printf("Part 2: %d\n", current.SolvePart2(input))
}

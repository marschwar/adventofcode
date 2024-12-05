package day05

import (
	"regexp"
	"slices"
	"sort"
	"strconv"
	"strings"
)

type rule struct {
	left  string
	right string
}

func SolvePart1(input string) int {
	rules := parseRules(input)
	updates := parseUpdates(input)

	results := make([][]string, 0, len(updates))
	for _, candidate := range updates {
		if isValidCandidate(candidate, rules) {
			results = append(results, candidate)
		}
	}

	result := 0
	for _, list := range results {
		middle, _ := strconv.Atoi(list[len(list)/2])
		result += middle
	}
	// fmt.Printf("Rules: %s\n", updates)
	return result
}

func SolvePart2(input string) int {
	rules := parseRules(input)
	updates := parseUpdates(input)

	reordered := make([][]string, 0, len(updates))
	for _, candidate := range updates {
		if !isValidCandidate(candidate, rules) {
			reordered = append(reordered, reorder(candidate, rules))
		}
	}

	result := 0
	for _, list := range reordered {
		middle, _ := strconv.Atoi(list[len(list)/2])
		result += middle
	}
	// fmt.Printf("Rules: %s\n", updates)
	return result
}

func parseUpdates(input string) [][]string {
	updatePattern := regexp.MustCompile(`(\d+(?:,\d+)+)\n`)
	updateMatches := updatePattern.FindAllStringSubmatch(input, -1)
	updates := make([][]string, len(updateMatches))
	for i, match := range updateMatches {
		updates[i] = strings.Split(match[1], ",")
	}
	return updates
}

func parseRules(input string) []rule {
	rulePattern := regexp.MustCompile(`(\d+)\|(\d+)\n`)
	ruleMatches := rulePattern.FindAllStringSubmatch(input, -1)
	rules := make([]rule, len(ruleMatches))
	for i, match := range ruleMatches {
		rules[i] = rule{match[1], match[2]}
	}
	return rules
}

func isValidCandidate(candidate []string, rules []rule) bool {
	for i := 1; i < len(candidate); i++ {
		if !isValidTuple(candidate[i-1], candidate[i], rules) {
			return false
		}
	}
	return true
}

func isValidTuple(before string, after string, rules []rule) bool {
	for _, rule := range rules {
		if rule.left == after && rule.right == before {
			return false
		}
	}
	return true
}

func reorder(original []string, rules []rule) []string {
	result := slices.Clone(original)
	sort.Slice(result, func(i, j int) bool {
		left := result[i]
		right := result[j]
		for _, rule := range rules {
			if rule.left == left && rule.right == right {
				return true
			}
			if rule.right == left && rule.left == right {
				return false
			}
		}
		return false
	})
	return result
}

package main

import (
	"fmt"
)

func main() {
	var command string
	for ; ; {
		fmt.Print("> ")
		_, err := fmt.Scan(&command)
		if err != nil || command == "exit" {
			return
		}
	}
}

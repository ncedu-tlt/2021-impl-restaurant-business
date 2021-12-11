package main

import (
	"fmt"
)

func controller(command string) {
	switch command {
	case "1":
		fmt.Println("Create")
	case "2":
		fmt.Println("Read")
	case "3":
		fmt.Println("Update")
	case "4":
		fmt.Println("Delete")
	default:
		fmt.Println("Не верная команда, попробуйте еще раз!")
	}
}


func main() {
	fmt.Println("1 - создание\n2 - чтение\n3 - обновление\n4 - удаление")
	var command string
	for ; ; {
		fmt.Print(" > ")
		_, err := fmt.Scan(&command)
		if err != nil || command == "exit" {
			return
		}
	}
}

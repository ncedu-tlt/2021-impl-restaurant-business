package main

import "fmt"

func main() {
	menu := []string{"Борщ", "Шашлык", "Чай"}
	fmt.Print("Приветствую! Сейчас в меню ")
	fmt.Print(len(menu))
	fmt.Println(" блюд(а)")
	fmt.Println("Выбери действие и введи соответствующий номер:")
	fmt.Println("1 - Изучить меню")
	fmt.Println("2 - Удалить блюдо")
	fmt.Println("3 - Добавить блюдо")
	fmt.Println("4 - Редактирование меню")
	var x int
	fmt.Scan(&x)
	for true {
		if x == 1 {
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
		}
		if x == 2 {
			fmt.Println("Какое блюдо хочешь удалить? (Введи номер)")
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
			var n int
			fmt.Scan(&n)
			menu = append(menu[:n-1], menu[n:]...)
			fmt.Println("Теперь меню выглядит так:")
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
			fmt.Println("")
		}
		if x == 3 {
			fmt.Println("Какое блюдо хочешь добавить (новое блюдо добавиться в конец меню)?")
			var nw string
			fmt.Scan(&nw)
			menu = append(menu,nw)
			fmt.Println("Теперь меню выглядит так:")
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
			fmt.Println("")
		}
		if x == 4 {
			fmt.Println("Какое блюдо хочешь изменить (введи номер)?")
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
			var r int
			fmt.Scan(&r)
			fmt.Println("Окей, на что хочешь изменить?")
			var re string
			fmt.Scan(&re)
			menu[r-1]=re
			fmt.Println("===============")
			for _, value := range menu {
				fmt.Println("-" + value + "-")
			}
			fmt.Println("===============")
			fmt.Println("")
		}
		if x < 1 || x > 4 {
			fmt.Println("Ошибка! Введи номер действия (1,2,3 или 4)")
		}
		fmt.Print("Сейчас в меню ")
		fmt.Print(len(menu))
		fmt.Println(" блюд(а)")
		fmt.Println("1 - Изучить меню")
		fmt.Println("2 - Удалить блюдо")
		fmt.Println("3 - Добавить блюдо")
		fmt.Println("4 - Редактирование меню")
		fmt.Scan(&x)
	}
}

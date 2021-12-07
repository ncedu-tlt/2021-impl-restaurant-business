package main

import (
    "fmt"
    "os"
)

func main() {
    var employees []string
    for true {
        var num int
        fmt.Println("Выберите пункт для редактирования списка или выхода из меню:\n1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit")
        fmt.Scan(&num)
        if num == 1 {
            var a string
            fmt.Println("Введите имя работника: ")
            fmt.Scan(&a)
            employees = append(employees, a)
        }

        if num == 2 {
            fmt.Println(employees)
        }
        if num == 3 {
            var j int
            var name string
            fmt.Println("Введите номер сотрудника, которого необходимо заменить: ")
            fmt.Scan(&j)
            fmt.Println("Введите имя нового сотрудника: ")
            fmt.Scan(&name)
            employees[j-1] = name

        }
        if num == 4 {
            var i int
            fmt.Println("Введите номер сотрудника, которого необходимо удалить(Отсчет с нуля): ")
            fmt.Scan(&i)
            employees = append(employees[:i], employees[i+1:]...)
        }
        if num == 5 {
            os.Exit(3)
        }
        if num < 1 || num > 5 {
            fmt.Println("Ошибка!")
        }
    }
}

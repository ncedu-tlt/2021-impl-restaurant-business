package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"os"
	"strconv"
)

type employee struct {
	id         int
	last_name  string
	first_name string
}

func main() {

	connStr := "user=postgres password=admin dbname=postgres sslmode=disable"
	db, err := sql.Open("postgres", connStr)

	// TODO: корректно обработать ошибку (err != nil - была ошибка при выполнении Open)
	// пример в Slack: канал 2021_ncedu_resources --> go_code_examples_2.zip --> cmd\7_database\database.go
	if err != nil {
		db.Exec("CREATE TABLE employee (id SERIAL PRIMARY KEY,first_name varchar(20),last_name varchar(20));")
		// TODO: обработать ошибку создания таблицы, не завершать программу в любом случае
	}
	defer db.Close()

	for true {

		var num int
		fmt.Println("Выберите пункт для редактирования списка или выхода из меню:\n1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit")
		fmt.Scan(&num)

		if num == 1 {

			var firstN string
			var lastN string
			fmt.Println("Введите имя нового сотрудника:")
			fmt.Scan(&firstN)
			fmt.Println("Введите фамилию нового сотрудника:")
			fmt.Scan(&lastN)

			// TODO: использовать плейсхолдеры ($1, $2 и т.д.) для передачи данных в SQL
			// пример: db.Exec("INSERT INTO person (first_name, last_name, email) VALUES ($1, $2, $3)", "Jason", "Moiron", "jmoiron@jmoiron.net")
			result, err := db.Exec("INSERT INTO employee (last_name, first_name) VALUES (" + lastN + "," + firstN + ");")

			if err != nil {
				// TODO: обработать ошибку, не используя panic (как минимум вывести на экран)
				panic(err)

			}

			// TODO: вывести что-то более понятное, например, "сотрудник Иванов Иван создан"
			fmt.Println(result.RowsAffected())

		}

		if num == 2 {

			rows, err := db.Query("select * from employee")
			if err != nil {
				// TODO: обработать ошибку, не используя panic (как минимум вывести на экран)
				panic(err)
			}
			defer rows.Close()
			products := []employee{}

			for rows.Next() {
				p := employee{}
				err := rows.Scan(&p.id, &p.last_name, &p.first_name)
				if err != nil {
					fmt.Println(err)
					continue
				}
				products = append(products, p)
			}
			for _, p := range products {
				fmt.Println(p.id, p.last_name, p.first_name)
			}
		}

		if num == 3 {

			var firstN string
			var lastN string
			var id int
			fmt.Println("Введите имя нового сотрудника:")
			fmt.Scan(&firstN)
			fmt.Println("Введите фамилию нового сотрудника:")
			fmt.Scan(&lastN)
			fmt.Println("Введите номер сотрудника, которого заменить:")
			fmt.Scan(&id)

			// TODO: использовать плейсхолдеры ($1, $2 и т.д.) для передачи данных в SQL
			// нет необходимости использовать strconv.Itoa
			result, err := db.Exec("UPDATE employee SET last_name = " + lastN + ",first_name =" + firstN + " WHERE id = " + strconv.Itoa(id) + ";")

			if err != nil {
				// TODO: обработать ошибку, не используя panic (как минимум вывести на экран)
				panic(err)

			}

			// TODO: вывести что-то более понятное, например, "сотрудник Иванов Иван изменен на Петров Петр"
			fmt.Println(result.RowsAffected())

		}

		if num == 4 {

			var id int
			fmt.Println("Введите номер сотрудника, которого удалить:")
			fmt.Scan(&id)

			// TODO: использовать плейсхолдеры ($1, $2 и т.д.) для передачи данных в SQL
			// нет необходимости использовать strconv.Itoa
			result, err := db.Exec("DELETE FROM employee WHERE id=" + strconv.Itoa(id) + ";")

			if err != nil {
				// TODO: нужно обработать ошибку (как минимум вывести на экран)
				// не использовать panic, т.к. она аварийно завершает программу
				panic(err)

			}

			// TODO: вывести что-то более понятное, например, "сотрудник Иванов Иван удален"
			fmt.Println(result.RowsAffected())

		}

		if num == 5 {
			// Почему 3? Лучше указывать 0 - нормальное завершение программы
			os.Exit(3)

		}

		if num < 1 || num > 5 {

			fmt.Println("Ошибка!")

		}
	}
}

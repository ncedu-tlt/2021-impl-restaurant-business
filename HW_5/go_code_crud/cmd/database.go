package main

import (
	"fmt"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"
	"log"
	"strconv"
)

type menu struct {
	id int
	name string
}

func main() {
	db, err := sqlx.Connect("postgres", "host=localhost user=postgres password=postgres dbname=postgres port=5432 sslmode=disable TimeZone=Europe/Samara")
	if err != nil {
		log.Fatalln("sqlx.Connect(): ", err)
	}

	for true {

		var num int
		fmt.Println("==========\nВыбери действие и введи соответствующий номер:\n1 - Изучить меню\n2 - Удалить блюдо\n3 - Добавить блюдо\n4 - Редактирование меню\n==========")
		fmt.Scan(&num)
		if num == 1 {

			rows, err := db.Query("select * from menu")
			if err != nil {
				fmt.Println("select * from menu: ", err)
			}
			defer rows.Close()
			products := []menu{}

			for rows.Next() {
				p := menu{}
				err := rows.Scan(&p.id, &p.name)
				if err != nil {
					fmt.Println(err)
					continue
				}
				products = append(products, p)
			}
			for _, p := range products {
				fmt.Println(p.id, p.name)
			}
		}
		if num == 2 {

			var id int
			fmt.Println("Введите номер блюда, которое хочешь удалить:")
			fmt.Scan(&id)

			_, err := db.Query("DELETE FROM menu WHERE id=" + strconv.Itoa(id) + ";")

			if err != nil {
				fmt.Println("DELETE FROM menu WHERE id", err)

			}
			fmt.Println("Блюдо было удалено")
		}



		if num == 3 {

			var name string
			fmt.Println("Введи название нового блюда (оно добавиться в конец меню):")
			fmt.Scan(&name)
			_, err := db.Exec("INSERT INTO menu (name) VALUES ($1)", name)

			if err != nil {
				fmt.Println("Новое блюдо добавлено")

			}
			fmt.Println("Добавлено новое блюдо")

		}

		if num == 4 {

			var id int
			var nw string
			fmt.Println("Введи номер блюда для изменения:")
			fmt.Scan(&id)
			fmt.Println("Введи новое блюдо:")
			fmt.Scan(&nw)

			_, err := db.Exec("UPDATE menu SET name = ($1) where id = ($2)", nw, id)

			if err != nil {
				fmt.Println("UPDATE menu")

			}
			fmt.Println("Блюдо изменено")

		}
		if num < 1 || num > 4 {
			fmt.Println("Ошибка!")
		}
	}
}
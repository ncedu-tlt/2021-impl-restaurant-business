package main

import (
	"fmt"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"
	"log"
	"os"
)

type menu struct {
	id   int
	name string
}

func main() {
	db, err := sqlx.Connect("postgres", "host=localhost user=postgres password=postgres dbname=postgres port=5432 sslmode=disable TimeZone=Europe/Samara")
	if err != nil {
		log.Fatalln("sqlx.Connect(): ", err)
	}
	_, err = db.Exec("create table menu(id serial,name varchar)")
	if err != nil {
		fmt.Println("Меню не создано ", err)
	}

	for true {

		var num int

		fmt.Println("==========\nВыбери действие и введи соответствующий номер:\n1 - Изучить меню\n2 - Удалить блюдо\n3 - Добавить блюдо\n4 - Редактирование меню\n5 - Выход\n==========")
		fmt.Scan(&num)
		if num == 1 {

			rows, err := db.Query("select * from menu")
			if err != nil {

				fmt.Println("Ошибка при изучении меню ", err)
				continue
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

			rw, err := db.Exec("DELETE FROM menu WHERE id=($1);", id)

			if err != nil {
				fmt.Println("Блюдо не удалено ", err)
				continue
			}
			if rowCount, _ := rw.RowsAffected(); rowCount > 0 {
				fmt.Println("Блюдо было удалено")
			} else {
				fmt.Println("Блюдо не было удалено")
			}
			if num == 3 {

				var name string
				fmt.Println("Введи название нового блюда (оно добавиться в конец меню):")
				fmt.Scan(&name)
				_, err := db.Exec("INSERT INTO menu (name) VALUES ($1)", name)

				if err != nil {

					fmt.Println("Новое блюдо не добавлено ", err)

					continue
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

				res, err := db.Exec("UPDATE menu SET name = ($1) where id = ($2)", nw, id)

				if err != nil {

					fmt.Println("Меню не изменено", err)
					continue
				}

				if rowCount, _ := res.RowsAffected(); rowCount > 0 {
					fmt.Printf("Блюдо с номером %d изменено на %s\n", id, nw)
				} else {
					fmt.Printf("Ошибка: Блюдо с номером %d не изменено\n", id)
				}

			}
			if num == 5 {
				os.Exit(0)
			}
			if num < 1 || num > 5 {
				fmt.Println("Ошибка!")
			}
		}
		db.Close()
	}
}

package main

import (
	"fmt"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"
	"log"
	"strconv"
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

	// TODO: Добавить создание таблицы (CREATE TABLE ...) и обработку ошибки создания

	for true {

		var num int

		// TODO: Добавить пункт "Выход"
		fmt.Println("==========\nВыбери действие и введи соответствующий номер:\n1 - Изучить меню\n2 - Удалить блюдо\n3 - Добавить блюдо\n4 - Редактирование меню\n==========")
		fmt.Scan(&num)
		if num == 1 {

			rows, err := db.Query("select * from menu")
			if err != nil {

				// TODO: Вместо "select * from menu: " вывести сообщение, понятное для обычного пользователя
				fmt.Println("select * from menu: ", err)

				// TODO: Отсюда вернуться к выбору пунктов
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

			// TODO: Сделать передачу id в SQL через плейсхолдер $1
			_, err := db.Query("DELETE FROM menu WHERE id=" + strconv.Itoa(id) + ";")

			if err != nil {
				// TODO: Вместо "DELETE FROM menu WHERE id" вывести сообщение, понятное для обычного пользователя
				fmt.Println("DELETE FROM menu WHERE id", err)

				// TODO: Отсюда вернуться к выбору пунктов
			}

			// TODO: Сделать проверку на удаление данных с помощью RowsAffected() и вывести соответствующие сообщения,
			// см. пример ниже (там, где UPDATE)
			fmt.Println("Блюдо было удалено")
		}

		if num == 3 {

			var name string
			fmt.Println("Введи название нового блюда (оно добавиться в конец меню):")
			fmt.Scan(&name)
			_, err := db.Exec("INSERT INTO menu (name) VALUES ($1)", name)

			if err != nil {

				// TODO: вывести корректное сообщение об ошибке, понятное для обычного пользователя, и саму ошибку
				fmt.Println("Новое блюдо добавлено")

				// TODO: Отсюда вернуться к выбору пунктов
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

				// TODO: вывести сообщение об ошибке, понятное для обычного пользователя, и саму ошибку
				fmt.Println("UPDATE menu")

				// TODO: Отсюда вернуться к выбору пунктов
			}

			// пример проверки изменения данных
			if rowCount, _ := res.RowsAffected(); rowCount > 0 {
				fmt.Printf("Блюдо с номером %d изменено на %s\n", id, nw)
			} else {
				fmt.Printf("Ошибка: Блюдо с номером %d не изменено\n", id)
			}

		}
		if num < 1 || num > 4 {
			fmt.Println("Ошибка!")
		}

		// TODO: Добавить закрытие базы данных
	}
}

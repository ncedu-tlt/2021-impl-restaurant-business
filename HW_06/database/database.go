package database

import (
	"fmt"
	"github.com/jmoiron/sqlx"
	"log"
)

type employees struct {
	id int `db:"id"`
	name string `db:"name"`
	surname string `db:"surname"`
	lastname string `db:"lastname"`
}

func ConnectDatabase(host string, user string, password string, dbname string, port string, sslMode string,
	timeZone string) *sqlx.DB {
	db, err := sqlx.Connect("postgres", "host=" + host + " user=" + user + " password=" + password +
		" dbname=" + dbname + " port=" + port + " sslmode=" + sslMode + " TimeZone=" + timeZone)
	if err != nil {
		log.Fatalln("sqlx.Connect(): ", err)
	}
	return db
}

func InsertRow(db *sqlx.DB, rowContent []string) {
	if len(rowContent) == 3 {
		_, err := db.Exec("INSERT INTO employees (id, name, surname, lastname) VALUES (DEFAULT, $1, $2, $3)",
			rowContent[0], rowContent[1], rowContent[2])
		if err != nil {
			fmt.Println("Не удалось вставить запись: ", err)
		}
	} else {
		fmt.Println("Не удалось вставить запись: слишком много/недостаточно аргументов")
	}
}

func ReadRow(db *sqlx.DB, rowId int)  {
	var employees []employees
	err := db.Select(&employees, "SELECT * FROM employees WHERE id=$1", rowId)
	if err != nil {
		fmt.Println("Не удалось считать строку: ", err)
		return
	}
	fmt.Println(employees)
}

func ReadTable(db *sqlx.DB) {
	var employees []employees
	err := db.Select(&employees, "SELECT * FROM employees")
	if err != nil {
		fmt.Println("Не удалось считать таблицу: ", err)
		return
	}
	for _, row := range employees {
		fmt.Println(row)
	}
}

func UpdateRow(db *sqlx.DB, rowId int, rowContent []string) {
	if len(rowContent) == 3 {
		_, err := db.Exec("UPDATE employees SET name=$1, surname=$2, lastname=$3 WHERE id=$4", rowContent[0], rowContent[1],
			rowContent[2], rowId)
		if err != nil {
			fmt.Println("Не удалось обновить запись: ", err)
		}
	} else {
		fmt.Println("Не удалось обновить запись: слишком много/недостаточно аргументов")
	}
}

func DeleteRow(db *sqlx.DB, rowId int) {
	_, err := db.Exec("DELETE FROM employees WHERE id=$1", rowId)
	if err != nil {
		fmt.Println("Не удалось считать удалить строку №", rowId, ": ", err)
	}
}


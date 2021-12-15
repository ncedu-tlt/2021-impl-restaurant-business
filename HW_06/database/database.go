package database

import (
	"fmt"
	"github.com/jmoiron/sqlx"
	"log"
)

type Employees struct {
	Id int `db:"id"`
	Name string `db:"name"`
	Surname string `db:"surname"`
	Lastname string `db:"lastname"`
}

func ConnectDatabase(host string, user string, password string, dbName string, port string, sslMode string) *sqlx.DB {
	db, err := sqlx.Connect("postgres", "host=" + host + " user=" + user + " password=" + password +
		" dbname=" + dbName + " port=" +port + " sslmode=" + sslMode)
	if err != nil {
		log.Fatalln("sqlx.Connect(): ", err)
	}
	_, err = db.Exec("create table if not exists Employees(id serial constraint table_name_pk primary key, " +
		"name varchar(32) not null, surname  varchar(32) not null, lastname varchar(32) not null); " +
		"alter table Employees owner to root; ")
	if err != nil {
		log.Fatalln("db.Exec(\"create table table_name\"): ", err)
	}
	return db
}


func InsertRow(db *sqlx.DB, rowContent []string) {
	if len(rowContent) == 3 {
		_, err := db.Exec("INSERT INTO Employees (id, name, surname, lastname) VALUES (DEFAULT, $1, $2, $3)",
			rowContent[0], rowContent[1], rowContent[2])
		if err != nil {
			fmt.Println("Не удалось вставить запись: ", err)
		}
	} else {
		fmt.Println("Не удалось вставить запись: слишком много/недостаточно аргументов")
	}
}

func ReadRow(db *sqlx.DB, rowId int)  {
	var employees []Employees
	err := db.Select(&employees, "SELECT * FROM employees WHERE id=$1", rowId)
	if err != nil {
		fmt.Println("Не удалось считать строку: ", err)
		return
	}
	fmt.Println(employees)
}

func ReadTable(db *sqlx.DB) {
	var employees []Employees
	err := db.Select(&employees, "SELECT * FROM Employees")
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
		_, err := db.Exec("UPDATE Employees SET name=$1, surname=$2, lastname=$3 WHERE id=$4", rowContent[0],
			rowContent[1], rowContent[2], rowId)
		if err != nil {
			fmt.Println("Не удалось обновить запись: ", err)
		}
	} else {
		fmt.Println("Не удалось обновить запись: слишком много/недостаточно аргументов")
	}
}

func DeleteRow(db *sqlx.DB, rowId int) {
	_, err := db.Exec("DELETE FROM Employees WHERE id=$1", rowId)
	if err != nil {
		fmt.Println("Не удалось считать удалить строку №", rowId, ": ", err)
	}
}


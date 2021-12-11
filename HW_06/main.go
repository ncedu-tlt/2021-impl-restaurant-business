package main

import (
	"HW_06/database"
	"bufio"
	"fmt"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"
	"os"
	"strconv"
	"strings"
)

func createOperation(db *sqlx.DB) {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Вставить строку")
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&operationCode)
		switch operationCode {
		case "0":
			return
		case "1":
			var columnValues string
			fmt.Print("Введите значения столбцов через пробел(без id): ")
			in := bufio.NewReader(os.Stdin)
			columnValues, _ = in.ReadString('\n')
			database.InsertRow(db, strings.Split(columnValues, " "))
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func readOperation(db *sqlx.DB) {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Считать строку\n(2) Считать таблицу")
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&operationCode)
		switch operationCode {
		case "0":
			return
		case "1":
			var rowId string
			fmt.Print("Введите номер строки: ")
			_, _ = fmt.Scan(&rowId)
			convertedRowId, err := strconv.Atoi(rowId)
			if err != nil {
				fmt.Println("Введен некорректный индекс")
				return
			}
			database.ReadRow(db, convertedRowId)
			return
		case "2":
			database.ReadTable(db)
			return
			default:
				fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func updateOperation(db *sqlx.DB) {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Обновить строку")
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&operationCode)
		switch operationCode {
		case "0":
			return
		case "1":
			var rowId string
			fmt.Print("Введите номер строки: ")
			_, _ = fmt.Scan(&rowId)
			convertedRowId, err := strconv.Atoi(rowId)
			if err != nil {
				fmt.Println("Введен некорректный индекс")
				return
			}
			var columnValues string
			fmt.Print("Введите значения столбцов через пробел(без id): ")
			in := bufio.NewReader(os.Stdin)
			columnValues, _ = in.ReadString('\n')
			database.UpdateRow(db, convertedRowId, strings.Split(columnValues, " "))
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func deleteOperation(db *sqlx.DB) {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Удалить строку")
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&operationCode)
		switch operationCode {
		case "0":
			return
		case "1":
			var rowId string
			fmt.Print("Введите номер строки: ")
			_, _ = fmt.Scan(&rowId)
			convertedRowId, err := strconv.Atoi(rowId)
			if err != nil {
				fmt.Println("Введен некорректный индекс")
				return
			}
			database.DeleteRow(db, convertedRowId)
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func controller(command string, db *sqlx.DB) {
	switch command {
	case "0":
		os.Exit(0)
	case "1":
		createOperation(db)
	case "2":
		readOperation(db)
	case "3":
		updateOperation(db)
	case "4":
		deleteOperation(db)
	default:
		fmt.Println("Команда не распознана, введите еще раз")
	}
}

func main() {
	db := database.ConnectDatabase("localhost", "root", "root", "test_db", "5432", "disable", "Europe/Samara")
	fmt.Println("Список команд:\n(0) Выход\n(1) создание\n(2) чтение \n(3) обновление \n(4) удаление")
	var command string
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&command)
		controller(command, db)
	}
}

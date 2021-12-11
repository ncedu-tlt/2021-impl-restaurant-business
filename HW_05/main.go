package main

import (
	"HW_05/table"
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func operationCreate(t *table.Table) {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Создать таблицу\n(2) Создать строку в таблице")
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&operationCode)
		switch operationCode {
		case "0":
			return
		case "2":
			var columnValues string
			fmt.Print("Введите значения столбцов через пробел: ")
			in := bufio.NewReader(os.Stdin)
			columnValues, _ = in.ReadString('\n')
			t.CreateRow(strings.Split(columnValues, " "))
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func operationRead(t *table.Table)  {
	var operationCode string
	fmt.Println("(0) Прервать операцию\n(1) Вывести строку\n(2) Вывести всю таблицу")
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
			t.ReadRow(convertedRowId)
			return
		case "2":
			t.ReadTable()
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func operationUpdate(t *table.Table)  {
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
			fmt.Print("Введите значения столбцов через пробел: ")
			in := bufio.NewReader(os.Stdin)
			columnValues, _ = in.ReadString('\n')
			t.UpdateRow(convertedRowId, strings.Split(columnValues, " "))
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func operationDelete(t *table.Table) {
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
			t.DeleteRow(convertedRowId)
			return
		default:
			fmt.Println("Команда не распознана, введите еще раз")
		}
	}
}

func controller(command string, t *table.Table) {
	switch command {
	case "0":
		os.Exit(0)
	case "1":
		operationCreate(t)
	case "2":
		operationRead(t)
	case "3":
		operationUpdate(t)
	case "4":
		operationDelete(t)
	default:
		fmt.Println("Не верная команда, попробуйте еще раз!")
	}
}


func main() {
	// таблица для тестов
	t := table.CreateTable("test_table", []string{ "col1", "col2", "col3" })
	t.CreateRow([]string{ "rty", "rtyrr", "rtyrey" })
	t.CreateRow([]string{ "cbvn", "yghfghj", "cvbndf" })
	t.CreateRow([]string{ "fhhgfn", "jhmbm", "wqgfh" })

	fmt.Println("Список команд:\n(0) Выход\n(1) создание\n(2) чтение \n(3) обновление \n(4) удаление")
	var command string
	for ; ; {
		fmt.Print(" > ")
		_, _ = fmt.Scan(&command)
		controller(command, t)
	}
}

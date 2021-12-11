package table

import "fmt"

type Table struct {
	name string
	table [][]string
	columnCounter int
}

func CreateTable(tableName string, columnNames []string) *Table {
	t := Table {
		name: tableName,
		columnCounter: len(columnNames),
	}
	t.table = append(t.table, columnNames)
	return &t
}

func (t *Table) CreateRow(rowValues []string) {
	if len(rowValues) == t.columnCounter {
		t.table = append(t.table, rowValues)
	} else {
		fmt.Println("Не удалось вставить строку: слишком много/мало аргументов")
	}
}

func (t *Table) ReadRow(rowId int) {
	if rowId < len(t.table) && rowId > 0 {
		for _, element := range t.table[rowId] {
			fmt.Print("\t", element)
		}
		fmt.Println()
	} else {
		fmt.Println("Не удалось считать строку: строки ", rowId, "не существует")
	}
}

func (t *Table) ReadTable() {
	for i := 0; i < len(t.table); i++ {
		for j := 0; j < t.columnCounter; j++ {
			fmt.Print("\t", t.table[i][j])
		}
		fmt.Println()
	}
}

func (t *Table) UpdateRow(rowId int, rowValues []string) {
	if len(rowValues) == t.columnCounter {
		if rowId < len(t.table) && rowId > 0 {
			t.table[rowId] = rowValues
		} else {
			fmt.Println("Не удалось обновить строку: строки", rowId, "не существует")
		}
	} else {
		fmt.Println("Не удалось обновить строку:  слишком много/мало аргументов")
	}

}

func (t *Table) DeleteRow(rowId int) {
	if rowId < len(t.table) && rowId > 0 {
		t.table = append(t.table[:rowId], t.table[rowId + 1:]...)
	} else {
		fmt.Println("Не удалось удалить строку: строки", rowId, "не существует")
	}
}
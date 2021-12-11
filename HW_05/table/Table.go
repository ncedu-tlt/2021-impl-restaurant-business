package table

type Table struct {
	columnNames []string
	table [][]string
}

func CreateTable(columnNames []string) *Table {
	t := &Table {
		columnNames: columnNames,
		table: [][]string{  },
	}
	return t
}

func (t *Table) CreateRow(rowContent []string) {
	t.table = append(t.table, rowContent)
}

func (t *Table) readRow(rowId int) []string {
	return t.table[rowId]
}

func (t *Table) readTable() [][]string {
	return t.table
}

func (t *Table) updateRow(rowId int, rowContent []string) {
	t.table[rowId] = rowContent
}

func (t *Table) deleteRow(rowId int) {
	t.table = append(t.table[:rowId], t.table[rowId + 1:]...)
}

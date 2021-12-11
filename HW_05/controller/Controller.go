package controller

import (
	"HW_05/table"
	"strings"
)

type Controller struct {
	tables map[string]table.Table
}

func createController() *Controller {
	c := &Controller {
		tables: make(map[string]table.Table),
	}
	return c
}

func (c *Controller) executeCommand(command string, level int) {

}

func (c *Controller) createTable(name string, columnNames string)  {
	c.tables[name] = *table.CreateTable(strings.Split(columnNames, " "))
}

func (c *Controller) createRow(tableName string, rowContent string) {
	if _, found := c.tables[tableName]; found {
		c.tables[tableName] = c.tables[tableName].CreateRow(strings.Split(rowContent, " "))
	} else {

	}
}

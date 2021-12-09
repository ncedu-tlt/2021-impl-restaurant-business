package main

import (
    "database/sql"
    "fmt"
    "os"
    "strconv"
)

type employee struct {
    id         int
    last_name  string
    first_name string
}

func main() {

    connStr := "user=postgres password=admin dbname=postgres sslmode=disable"
    db, err := sql.Open("postgres", connStr)
    if err != nil {
        db.Exec("CREATE TABLE employee (id SERIAL PRIMARY KEY,first_name varchar(20),last_name varchar(20));")
    }
    defer db.Close()

    for true {

        var num int
        fmt.Println("Выберите пункт для редактирования списка или выхода из меню:\n1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit")
        fmt.Scan(&num)

        if num == 1 {

            var firstN string
            var lastN string
            fmt.Println("Введите имя нового сотрудника:")
            fmt.Scan(&firstN)
            fmt.Println("Введите фамилию нового сотрудника:")
            fmt.Scan(&lastN)

            result, err := db.Exec("INSERT INTO employee (last_name, first_name) VALUES (" + lastN + "," + firstN + ");")

            if err != nil {

                panic(err)

            }

            fmt.Println(result.RowsAffected())

        }

        if num == 2 {

            rows, err := db.Query("select * from employee")
            if err != nil {
                panic(err)
            }
            defer rows.Close()
            products := []employee{}

            for rows.Next() {
                p := employee{}
                err := rows.Scan(&p.id, &p.last_name, &p.first_name)
                if err != nil {
                    fmt.Println(err)
                    continue
                }
                products = append(products, p)
            }
            for _, p := range products {
                fmt.Println(p.id, p.last_name, p.first_name)
            }
        }

        if num == 3 {

            var firstN string
            var lastN string
            var id int
            fmt.Println("Введите имя нового сотрудника:")
            fmt.Scan(&firstN)
            fmt.Println("Введите фамилию нового сотрудника:")
            fmt.Scan(&lastN)
            fmt.Println("Введите номер сотрудника, которого заменить:")
            fmt.Scan(&id)

            result, err := db.Exec("UPDATE employee SET last_name = " + lastN + ",first_name =" + firstN + " WHERE id = " + strconv.Itoa(id) + ";")

            if err != nil {

                panic(err)

            }

            fmt.Println(result.RowsAffected())

        }

        if num == 4 {

            var id int
            fmt.Println("Введите номер сотрудника, которого удалить:")
            fmt.Scan(&id)

            result, err := db.Exec("DELETE FROM employee WHERE id=" + strconv.Itoa(id) + ";")

            if err != nil {

                panic(err)

            }

            fmt.Println(result.RowsAffected())

        }

        if num == 5 {

            os.Exit(3)

        }

        if num < 1 || num > 5 {

            fmt.Println("Ошибка!")

        }
    }
}

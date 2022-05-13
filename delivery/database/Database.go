package database

import (
	"delivery/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"
	"os"
)

var Database *gorm.DB

func ConnectDatabase() {
	databaseUrl, exists := os.LookupEnv("DATABASE_URL")
	if !exists {
		log.Fatalln("Can't find DATABASE_URL env!")
	}

	db, err := gorm.Open(postgres.New(postgres.Config{
		DSN:                  databaseUrl,
		PreferSimpleProtocol: true,
	}), &gorm.Config{})

	if err != nil {
		log.Fatalln(err.Error())
	}

	err = db.AutoMigrate(&model.Order{}, &model.OrderStatus{}, &model.OrderToDish{}, &model.PaymentType{})
	if err != nil {
		log.Fatalln(err)
	}

	Database = db
}

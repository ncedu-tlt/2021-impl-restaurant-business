package main

import (
	"delivery/database"
	"delivery/routes"
	"github.com/gin-gonic/gin"
	"log"
	"os"
)

func main() {
	router := gin.Default()

	routes.SetupRoutes(router)

	database.ConnectDatabase()

	port, exists := os.LookupEnv("PORT")
	if !exists {
		port = "5000"
	}

	if err := router.Run(":" + port); err != nil {
		log.Fatalln(err.Error())
	}
}

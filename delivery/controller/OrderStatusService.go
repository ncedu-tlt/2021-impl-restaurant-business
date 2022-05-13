package controller

import (
	"delivery/database"
	"delivery/model"
	"errors"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
	"log"
	"net/http"
)

func CreateOrderStatus(context *gin.Context) {
	type orderStatusInput struct {
		Name string `json:"name" binding:"required"`
	}

	var input orderStatusInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	var orderStatus model.OrderStatus
	orderStatus.Name = input.Name

	if err := database.Database.Create(&orderStatus).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func FindAllOrderStatuses(context *gin.Context) {
	var orderStatuses []model.OrderStatus

	if err := database.Database.Find(&orderStatuses).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.JSON(http.StatusOK, orderStatuses)
}

func FindOrderStatusById(context *gin.Context) {
	var orderStatus model.OrderStatus

	if err := database.Database.First(&orderStatus, context.Param("id")).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.JSON(http.StatusOK, orderStatus)
}

func UpdateOrderStatusById(context *gin.Context) {
	var orderStatus model.OrderStatus

	if err := database.Database.First(&orderStatus, context.Param("id")).Error; err != nil {
		var status int

		if errors.Is(err, gorm.ErrRecordNotFound) {
			status = http.StatusNotFound
		} else {
			status = http.StatusInternalServerError
		}

		context.AbortWithStatusJSON(status, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	type orderStatusInput struct {
		Name string `json:"name" binding:"required"`
	}

	var input orderStatusInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	orderStatus.Name = input.Name

	if err := database.Database.Save(orderStatus).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func DeleteOrderStatusById(context *gin.Context) {
	if err := database.Database.Delete(&model.OrderStatus{}, context.Param("id")).Error; err != nil {
		log.Println(err)
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
	}

	context.Status(http.StatusOK)
}

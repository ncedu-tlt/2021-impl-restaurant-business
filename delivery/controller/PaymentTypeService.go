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

func CreatePaymentType(context *gin.Context) {
	type paymentTypeInput struct {
		Name string `json:"name" binding:"required"`
	}

	var input paymentTypeInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	var paymentType model.PaymentType
	paymentType.Name = input.Name

	if err := database.Database.Create(&paymentType).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func FindAllPaymentTypes(context *gin.Context) {
	var paymentTypes []model.PaymentType

	if err := database.Database.Find(&paymentTypes).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.JSON(http.StatusOK, paymentTypes)
}

func FindPaymentTypeById(context *gin.Context) {
	var paymentType model.PaymentType

	if err := database.Database.First(&paymentType, context.Param("id")).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.JSON(http.StatusOK, paymentType)
}

func UpdatePaymentTypeById(context *gin.Context) {
	var paymentType model.PaymentType

	if err := database.Database.First(&paymentType, context.Param("id")).Error; err != nil {
		var status int

		if errors.Is(err, gorm.ErrRecordNotFound) {
			status = http.StatusNotFound
		} else {
			status = http.StatusInternalServerError
		}

		context.JSON(status, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	type paymentTypeInput struct {
		Name string `json:"name" binding:"required"`
	}

	var input paymentTypeInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	paymentType.Name = input.Name

	if err := database.Database.Save(paymentType).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func DeletePaymentTypeById(context *gin.Context) {
	if err := database.Database.Delete(&model.PaymentType{}, context.Param("id")).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

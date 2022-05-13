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

func CreateOrder(context *gin.Context) {
	type orderInput struct {
		Address       string `json:"address"         binding:"required"`
		Cost          uint   `json:"cost"            binding:"required"`
		PaymentStatus *bool  `json:"payment_status"  binding:"required"`
		PaymentTypeId uint   `json:"payment_type_id" binding:"required,gte=1"`
		OrderStatusId uint   `json:"order_status_id" binding:"required,gte=1"`
	}

	var input orderInput

	if err := context.ShouldBind(&input); err != nil {
		context.AbortWithStatusJSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		return
	}

	order := model.Order{Address: input.Address, Cost: input.Cost, PaymentStatus: *input.PaymentStatus,
		PaymentTypeId: input.PaymentTypeId, OrderStatusId: input.OrderStatusId}

	if err := database.Database.Create(&order).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func FindAllOrders(context *gin.Context) {
	var orders []model.Order

	if err := database.Database.Find(&orders).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	for i := range orders {
		if err := database.Database.First(&orders[i].OrderStatus, orders[i].OrderStatusId).Error; err != nil {
			context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
			log.Println(err)
			return
		}

		if err := database.Database.First(&orders[i].PaymentType, orders[i].PaymentTypeId).Error; err != nil {
			context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
			log.Println(err)
			return
		}
	}

	context.JSON(http.StatusOK, orders)
}

func FindOrderById(context *gin.Context) {
	var order model.Order

	if err := database.Database.First(&order, context.Param("id")).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	if err := database.Database.First(&order.OrderStatus, order.OrderStatusId).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	if err := database.Database.First(&order.PaymentType, order.PaymentTypeId).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.JSON(http.StatusOK, order)
}

func UpdateOrderById(context *gin.Context) {
	var order model.Order

	if err := database.Database.First(&order, context.Param("id")).Error; err != nil {
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

	type orderInput struct {
		Address       *string `json:"address"`
		Cost          *uint   `json:"cost"`
		PaymentStatus *bool   `json:"payment_status"`
		PaymentTypeId *uint   `json:"payment_type_id"`
		OrderStatusId *uint   `json:"order_status_id"`
	}

	var input orderInput

	if err := context.ShouldBind(&input); err != nil {
		context.AbortWithStatusJSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	if input.Address != nil {
		order.Address = *input.Address
	}
	if input.Cost != nil {
		order.Cost = *input.Cost
	}
	if input.PaymentStatus != nil {
		order.PaymentStatus = *input.PaymentStatus
	}
	if input.PaymentTypeId != nil {
		order.PaymentTypeId = *input.PaymentTypeId
	}
	if input.OrderStatusId != nil {
		order.OrderStatusId = *input.OrderStatusId
	}

	if err := database.Database.Save(&order).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func DeleteOrderById(context *gin.Context) {
	if err := database.Database.Delete(&model.Order{}, context.Param("id")).Error; err != nil {
		context.AbortWithStatusJSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

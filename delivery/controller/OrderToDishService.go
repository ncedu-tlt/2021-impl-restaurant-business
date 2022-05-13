package controller

import (
	"delivery/database"
	"delivery/model"
	"encoding/json"
	"errors"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
	"io/ioutil"
	"log"
	"net/http"
	"strconv"
)

func CreateOrderToDish(context *gin.Context) {
	type orderToDishInput struct {
		OrderId  uint `json:"order_id" binding:"required"`
		DishId   uint `json:"dish_id" binding:"required"`
		Quantity uint `json:"quantity" binding:"required,gte=1"`
	}

	var input orderToDishInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	// TODO: ask about GIN replacement
	type response struct {
		Status bool `json:"status"`
	}

	var dishStatus response

	requestResponse, err := http.Get("")
	if err != nil {
		context.JSON(http.StatusServiceUnavailable, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	responseData, err := ioutil.ReadAll(requestResponse.Body)
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	err = json.Unmarshal(responseData, &dishStatus)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	if !dishStatus.Status {
		message := "dishes: record with id=" +
			strconv.FormatUint(uint64(input.DishId), 10) + " not found"
		context.JSON(http.StatusBadRequest, gin.H{"message": message})
		log.Println(message)
		return
	}

	orderToDish := model.OrderToDish{OrderId: input.OrderId, DishId: input.DishId,
		Quantity: input.Quantity}

	if err := database.Database.Create(&orderToDish).Error; err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func FindDishesByOrderId(context *gin.Context) {
	type dish struct {
		DishId   uint `json:"dish_id"`
		Quantity uint `json:"quantity"`
	}

	type orderToDishesOutput struct {
		OrderId uint   `json:"order_id"`
		Dishes  []dish `json:"dishes"`
	}

	var output orderToDishesOutput

	if err := context.ShouldBind(&output); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	var orderToDishes []model.OrderToDish
	if err := database.Database.Find(&orderToDishes, context.Param("id")).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	for _, element := range orderToDishes {
		output.Dishes = append(output.Dishes, dish{element.DishId, element.Quantity})
	}

	context.JSON(http.StatusOK, output)
}

func UpdateDishByOrderIdAndDishId(context *gin.Context) {
	type orderToDishInput struct {
		Quantity uint `json:"quantity" binding:"required,gte=1"`
	}

	var input orderToDishInput

	if err := context.ShouldBind(&input); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	var orderToDish model.OrderToDish

	if err := database.Database.Where("order_id = ? AND dish_id = ?", context.Param("order_id"),
		context.Param("dish_id")).First(&orderToDish).Error; err != nil {

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

	orderToDish.Quantity = input.Quantity

	if err := database.Database.Save(orderToDish).Error; err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

func DeleteDishByOrderIdAndDishId(context *gin.Context) {
	if err := database.Database.Where("order_id = ? AND dish_id = ?", context.Param("order_id"),
		context.Param("dish_id")).Delete(&model.OrderToDish{}).Error; err != nil {

		context.JSON(http.StatusInternalServerError, gin.H{"message": err.Error()})
		log.Println(err)
		return
	}

	context.Status(http.StatusOK)
}

package routes

import (
	"delivery/controller"
	_ "delivery/docs"
	"github.com/gin-gonic/gin"
	ginSwagger "github.com/swaggo/gin-swagger"
	"github.com/swaggo/gin-swagger/swaggerFiles"
)

func SetupRoutes(router *gin.Engine) {
	orderGroup := router.Group("/orders")
	{
		orderGroup.GET("all", controller.FindAllOrders)
		orderGroup.GET("order/id=:id", controller.FindOrderById)
		orderGroup.POST("new", controller.CreateOrder)
		orderGroup.PATCH("order/update/id=:id", controller.UpdateOrderById)
		orderGroup.DELETE("order/delete/id=:id", controller.DeleteOrderById)
	}

	orderStatusGroup := router.Group("/statuses")
	{
		orderStatusGroup.GET("all", controller.FindAllOrderStatuses)
		orderStatusGroup.GET("status/id=:id", controller.FindOrderStatusById)
		orderStatusGroup.POST("new", controller.CreateOrderStatus)
		orderStatusGroup.PATCH("status/update/id=:id", controller.UpdateOrderStatusById)
		orderStatusGroup.DELETE("status/delete/id=:id", controller.DeleteOrderStatusById)
	}

	paymentTypeGroup := router.Group("types")
	{
		paymentTypeGroup.GET("all", controller.FindAllPaymentTypes)
		paymentTypeGroup.GET("type/id=:id", controller.FindPaymentTypeById)
		paymentTypeGroup.POST("new", controller.CreatePaymentType)
		paymentTypeGroup.PATCH("type/update/id=:id", controller.UpdatePaymentTypeById)
		paymentTypeGroup.DELETE("type/delete/id=:id", controller.DeletePaymentTypeById)
	}

	orderToDishGroup := router.Group("dishes")
	{
		orderToDishGroup.GET("all/id=:id", controller.FindDishesByOrderId)
		orderToDishGroup.POST("new", controller.CreateOrderToDish)
		orderToDishGroup.PATCH("dish/update/order_id=:order_id/dish_id=:dish_id",
			controller.UpdateDishByOrderIdAndDishId)
		orderToDishGroup.DELETE("dish/delete/order_id=:order_id/dish_id=:dish_id",
			controller.DeleteDishByOrderIdAndDishId)
		orderToDishGroup.DELETE("dish/delete/id=:id", controller.DeleteDishesByOrderId)
	}

	router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))
}

package model

type OrderToDish struct {
	OrderId uint  `gorm:"column:order_id;type:int;not null;primaryKey" json:"order_id"`
	DishId	uint  `gorm:"column:dish_id;type:int;not null;primaryKey" json:"dish_id"`
	Quantity uint `gorm:"column:quantity;type:int;not null;default:1" json:"quantity"`

	Order 	Order `gorm:"constraint:OnUpdate:CASCADE,OnDelete:SET NULL" json:"-"`
}

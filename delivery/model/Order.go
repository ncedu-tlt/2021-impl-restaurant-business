package model

type Order struct {
	Id      	  uint  		`gorm:"column:id;type:int;not null;autoIncrement;unique;primaryKey" json:"id"`
	Address 	  string 		`gorm:"column:address;type:varchar(256);not null;" json:"address"`
	Cost          uint 	 		`gorm:"column:cost;type:int;not null" json:"cost"`
	PaymentStatus bool   		`gorm:"column:payment_status;type:bool;not null" json:"payment_status"`
	PaymentTypeId uint   		`gorm:"column:payment_type_id;type:int;not_null" json:"-"`
	OrderStatusId uint	 		`gorm:"column:order_status_id;type:int;not_null" json:"-"`

	PaymentType	  PaymentType	`gorm:"constraint:OnUpdate:cascade,OnDelete:set null" json:"payment_type"`
	OrderStatus   OrderStatus   `gorm:"constraint:OnUpdate:cascade,OnDelete:set null" json:"order_status"`
	OrderToDishes []OrderToDish `gorm:"constraint:OnUpdate:cascade,OnDelete:set null" json:"-"`
}
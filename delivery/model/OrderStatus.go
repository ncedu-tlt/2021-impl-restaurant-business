package model

type OrderStatus struct {
	Id     uint    `gorm:"column:id;type:int;not null;autoIncrement;unique;primaryKey" json:"id"`
	Name   string  `gorm:"column:name;type:varchar(32);not null;" json:"name"`

	Orders []Order `gorm:"constraint:OnUpdate:CASCADE,OnDelete:SET NULL" json:"-"`
}

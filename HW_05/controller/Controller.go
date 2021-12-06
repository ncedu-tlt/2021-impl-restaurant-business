package controller

type State int
const (
	_ State = iota
	Create
	Read
	Update
	Delete
)

type Controller struct {
	state State
}

func (c Controller) SetState(state State) {
	c.state = state
}
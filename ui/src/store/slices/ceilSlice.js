import {createSlice} from '@reduxjs/toolkit'
import {minHeight, minWidth} from "../../consts";

export const ceilSlice = createSlice({
	name: 'ceils',
	initialState: {
		width: minWidth,
		height: minHeight,
	},
	reducers: {
		changeWidth: (state, action) => {
			state.width = action.payload
		},
		changeHeight: (state, action) => {
			state.height = action.payload
		},
	},
})

export const {
	changeWidth,
	changeHeight,
} = ceilSlice.actions

export default ceilSlice.reducer
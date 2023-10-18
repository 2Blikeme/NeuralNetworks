import {createSlice} from '@reduxjs/toolkit'
import {minHeight, minWidth} from "../../consts";

const createCleanMatrix = (n, m) => {
	return Array.from({length: n}, () => Array(m).fill(0));
}

export const ceilSlice = createSlice({
	name: 'ceils',
	initialState: {
		width: minWidth,
		height: minHeight,
		matrix: createCleanMatrix(minHeight, minWidth)
	},
	reducers:
		{
			changeWidth: (state, action) => {
				state.width = action.payload
				state.matrix = createCleanMatrix(state.height, state.width)
			},
			changeHeight:
				(state, action) => {
					state.height = action.payload
					state.matrix = createCleanMatrix(state.height, state.width)
				},
			changeMatrix:
				(state, action) => {
					const {i, j} = action.payload.key
					const changeColor = action.payload.changeColor
					const newMatrix = state.matrix.map((row) => [...row]);
					newMatrix[i][j] = changeColor ? 1 : 0;
					state.matrix = newMatrix;
				},
		},
})

export const {
	changeWidth,
	changeHeight,
	changeMatrix,
} = ceilSlice.actions

export default ceilSlice.reducer
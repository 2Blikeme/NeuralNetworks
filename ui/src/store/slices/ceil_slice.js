import {createSlice} from '@reduxjs/toolkit'
import {minHeight, minWidth} from "../../consts";

const createCleanMatrix = (n, m) => {
	return Array.from({length: n}, () => Array(m).fill(0));
}

export const ceil_slice = createSlice({
	name: 'ceils',
	initialState: {
		width: minWidth,
		height: minHeight,
		matrix: createCleanMatrix(minHeight, minWidth)
	},
	reducers:
		{
			clearMatrix: (state) => {
				state.matrix = createCleanMatrix(state.width, state.height);
			},
			changeMatrix: (state, action) => {
				const {i, j} = action.payload.key
				const changeColor = action.payload.changeColor
				const newMatrix = state.matrix.map((row) => [...row]);
				newMatrix[i][j] = changeColor ? 1 : 0;
				state.matrix = newMatrix;
				console.log(newMatrix)
			},
		},
})

export const {
	changeMatrix,
	clearMatrix,
} = ceil_slice.actions

export default ceil_slice.reducer
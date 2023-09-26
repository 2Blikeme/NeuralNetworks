import {configureStore} from '@reduxjs/toolkit'
import {ceilSlice} from "./slices/ceilSlice";

export default configureStore({
	reducer: {
		ceils: ceilSlice.reducer
	},
})
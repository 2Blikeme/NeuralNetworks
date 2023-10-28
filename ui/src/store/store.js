import {configureStore} from '@reduxjs/toolkit'
import {ceil_slice} from "./slices/ceil_slice";

export default configureStore({
	reducer: {
		ceils: ceil_slice.reducer
	},
})
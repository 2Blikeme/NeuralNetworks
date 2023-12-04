import {deleteRequest, getRequest, postRequest} from "../utils/ApiUtils";
import {host} from "../consts/consts";
import {CREATE_MATRIX_SERVICE, DELETE_MATRIX_SERVICE} from "../consts/services";

export const saveMatrix = ({id, matrix}) => {
	const  serviceULR = host + CREATE_MATRIX_SERVICE
	postRequest(serviceULR, {
		id: id,
		matrixInfo: {
			matrix: matrix
		}
	})
}

export const deleteMatrix = ({ids}) => {
	const serviceURL = host + DELETE_MATRIX_SERVICE
	deleteRequest(serviceURL, {
		ids: ids
	})
}

import {deleteRequest, getRequest} from "../utils/ApiUtils";
import {host} from "../consts/consts";
import {DELETE_MATRIX_SERVICE} from "../consts/services";

export const deleteMatrix = ({ids}) => {
	const serviceURL = host + DELETE_MATRIX_SERVICE
	deleteRequest(serviceURL, {
		ids: ids
	})
}

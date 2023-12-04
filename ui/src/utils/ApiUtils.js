import axios from "axios";

export const getRequest = (url) => {
	return axios.get(url).then((response) => {
		return response
	})
}

export const postRequest = (url, body) => {
	return axios.post(url, body)
		.then((response) => {
			return response
		})
}

export const deleteRequest = (url, body) => {
	return axios.delete(url, {
		data: body,
	}).then((response) => {
		return response
	})
}


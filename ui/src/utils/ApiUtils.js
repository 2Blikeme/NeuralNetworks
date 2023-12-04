import axios from "axios";

export const getRequest = (url, body) => {
	axios.get(url, {
		data: body,
	}).then((response) => {
		return response
	})
}

export const postRequest = (url, body) => {
	axios.post(url, body)
		.then((response) => {
			return response
		})
}

export const deleteRequest = (url, body) => {
	axios.delete(url, {
		data: body,
	}).then((response) => {
		return response
	})
}


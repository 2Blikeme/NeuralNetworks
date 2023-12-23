import {host} from "../consts/consts";
import {PREDICT_HEBB, TRAIN_NETWORK} from "../consts/services";
import {getRequest, postRequest} from "../utils/ApiUtils";

export const trainNetwork = (metadata) => {
	const service = host + TRAIN_NETWORK
	return postRequest(service, metadata)
}

export const predict = () => {
  const service = host + PREDICT_HEBB
	return getRequest(service)
}

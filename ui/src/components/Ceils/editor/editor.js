import React, {useState} from 'react';
import "./editor.css"
import {maxHeight, maxWidth, minHeight, minWidth} from "../../../consts";
import {useDispatch, useSelector} from "react-redux";
import {changeHeight, changeWidth} from "../../../store/slices/ceilSlice";

const Editor = () => {

	const [width, setWidth] = useState()
	const [height, setHeight] = useState()


	const dispatch = useDispatch()

	const onWidthInputListener = (event) => {
		dispatch(changeWidth(validateInputData(event.target.value, minWidth, maxWidth, setWidth)))
	}

	const onHeightInputListener = (event) => {
		dispatch(changeHeight(validateInputData(event.target.value, minHeight, maxHeight, setHeight)))
	}

	const validateInputData = (value, min, max, callback) => {
		if (!isNumber(value)) {
			callback(min)
			return min
		}
		if (value > max || value < 0) {
			if (value / 2 > (max / 2)) {
				callback(max)
				return max
			} else {
				callback(min)
				return min
			}
		} else {
			callback(value)
			return value
		}
	}

	const isNumber = (str) => {
		if (str.trim() === '') {
			return false;
		}
		return !isNaN(str);
	}

	return (
		<div className={"editor-container"}>
			<h1>
				Change size
			</h1>
			<div className={"editor-input"}>
				<input value={width}
					   onChange={onWidthInputListener}
					   type={"text"}
					   placeholder={"Width"}
					   pattern={"[0-9]*"}
				/>
			</div>
			<div className={"editor-input"}>
				<input value={height}
					   onChange={onHeightInputListener}
					   type={"text"}
					   placeholder={"Height"}
					   pattern={"[0-9]*"}/>
			</div>
		</div>
	);
};

export default Editor;
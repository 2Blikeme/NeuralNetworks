import React from 'react';
import CeilDesk from "../ceils/desk/ceil_desk";
import './data_input.css'
import CustomButton from "../buttons/button/custom_button";
import {useDispatch, useSelector} from "react-redux";
import {clearMatrix} from "../../store/slices/ceil_slice";

function DataInput(props) {

	const dispatch = useDispatch()
	const inputMatrix = useSelector((state) => state.ceils.matrix)

	const clearDataButtonListener = () => {
		dispatch(clearMatrix())
	}

	return (
		<div className={"data-input-container"}>
			<CeilDesk matrix={inputMatrix}/>
			<CustomButton onClick={clearDataButtonListener} buttonText={'Clear'}/>
		</div>
	);
}

export default DataInput;
import React from 'react';
import CustomButton from "../buttons/button/custom_button";
import CeilDesk from "../ceils/desk/ceil_desk";
import './saved_values.css'
import {deleteMatrix, saveMatrix} from "../../services/MatrixService";
import {useSelector} from "react-redux";

function SavedValues({name}) {

	const matrix = useSelector((state) => state.ceils.matrix)

	const clearButtonClickListener = () => {
		deleteMatrix({ids: [name]})
	}

	const saveMatrixClickListener = () => {
		saveMatrix({id: name, matrix: matrix})
	}

	return (
		<div className={"saved-matrix-container"}>
			<div className={"saved-matrix-buttons-container"}>
				<CustomButton onClick={saveMatrixClickListener} styles={{flex: '50%'}} buttonText={name}/>
				<CustomButton onClick={clearButtonClickListener} styles={{flex: '50%'}} buttonText={'Clear'}/>
			</div>
			<CeilDesk disabled={true}/>
		</div>
	);
}

export default SavedValues;

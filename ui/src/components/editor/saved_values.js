import React, {useEffect, useState} from 'react';
import CustomButton from "../buttons/button/custom_button";
import CeilDesk from "../ceils/desk/ceil_desk";
import './saved_values.css'
import {deleteMatrix, getMatrix, saveMatrix} from "../../services/MatrixService";
import {useSelector} from "react-redux";

function SavedValues({name}) {

	const [matrix, setMatrix] = useState(undefined)

	const matrixField = useSelector((state) => state.ceils.matrix)

	const clearButtonClickListener = () => {
		deleteMatrix({ids: [name]}).then((response) => {
			setMatrix(undefined)
		})
	}

	const saveMatrixClickListener = () => {
		saveMatrix({id: name, matrix: matrixField}).then((response) => {
			setMatrix(response.data.matrixInfo.matrix)
		})
	}

	useEffect(() => {
		if (!matrix) {
			getMatrix(name)
				.then((response) => {
					setMatrix(response.data.matrixInfo.matrix)
				})
		}
	}, []);

	return (
		<div className={"saved-matrix-container"}>
			<div className={"saved-matrix-buttons-container"}>
				<CustomButton onClick={saveMatrixClickListener} styles={{flex: '50%'}} buttonText={name}/>
				<CustomButton onClick={clearButtonClickListener} styles={{flex: '50%'}} buttonText={'Clear'}/>
			</div>
			<CeilDesk matrix={matrix} disabled={true}/>
		</div>
	);
}

export default SavedValues;

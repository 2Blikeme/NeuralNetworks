import React from 'react';
import CustomButton from "../buttons/button/custom_button";
import CeilDesk from "../ceils/desk/ceil_desk";
import './saved_values.css'
import {deleteMatrix} from "../../services/MatrixService";

function SavedValues({name}) {

	const clearButtonClickListener = () => {
		deleteMatrix({ids: [name]})
	}

	return (
		<div className={"saved-matrix-container"}>
			<div className={"saved-matrix-buttons-container"}>
				<CustomButton styles={{flex: '50%'}} buttonText={name}/>
				<CustomButton onClick={clearButtonClickListener} styles={{flex: '50%'}} buttonText={'Clear'}/>
			</div>
			<CeilDesk disabled={true}/>
		</div>
	);
}

export default SavedValues;

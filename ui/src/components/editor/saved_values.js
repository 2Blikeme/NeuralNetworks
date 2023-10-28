import React from 'react';
import CustomButton from "../buttons/button/custom_button";
import CeilDesk from "../Ceils/desk/ceil_desk";
import './saved_values.css'

function SavedValues({name}) {

	return (
		<div className={"saved-matrix-container"}>
			<CustomButton buttonText={name}/>
			<CeilDesk disabled={true}/>
		</div>
	);
}

export default SavedValues;

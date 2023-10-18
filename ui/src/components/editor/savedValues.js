import React from 'react';
import CustomButton from "../buttons/button/CustomButton";
import CeilDesk from "../Ceils/desk/CeilDesk";
import './savedValues.css'

function SavedValues({name}) {

	return (
		<div className={"container"}>
			<CustomButton buttonText={name}/>
			<CeilDesk disabled={true}
					  lowSize={true}/>
		</div>
	);
}

export default SavedValues;
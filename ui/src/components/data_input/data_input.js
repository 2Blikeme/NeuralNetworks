import React from 'react';
import CeilDesk from "../ceils/desk/ceil_desk";
import './data_input.css'
import CustomButton from "../buttons/button/custom_button";

function DataInput(props) {
	return (
		<div className={"data-input-container"}>
			<CeilDesk/>
			<CustomButton buttonText={'Clear'} />
		</div>
	);
}

export default DataInput;
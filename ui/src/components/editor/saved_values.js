import React from 'react';
import Custom_button from "../buttons/button/custom_button";
import Ceil_desk from "../Ceils/desk/ceil_desk";
import './saved_values.css'

function Saved_values({name}) {

	return (
		<div className={"container"}>
			<Custom_button buttonText={name}/>
			<Ceil_desk disabled={true}
					   lowSize={true}/>
		</div>
	);
}

export default Saved_values;
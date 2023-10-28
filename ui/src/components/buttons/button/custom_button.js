import React from 'react';
import './custom_button.css'

function CustomButton({
						  buttonText = '',
						  styles,
					  }) {
	return (
		<div className={"button-container"}>
			<button className={"button"} style={styles}>
				{buttonText}
			</button>
		</div>
	);
}

export default CustomButton;
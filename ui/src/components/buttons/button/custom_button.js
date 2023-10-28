import React from 'react';
import './custom_button.css'

function CustomButton({
						  buttonText = '',
						  styles,
					  }) {
	return (
		<button className={"button"} style={styles}>
			{buttonText}
		</button>
	);
}

export default CustomButton;
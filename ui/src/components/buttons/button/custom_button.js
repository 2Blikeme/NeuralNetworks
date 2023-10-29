import React from 'react';
import './custom_button.css'

function CustomButton({
						  buttonText = '',
						  styles, onClick,
					  }) {
	return (
		<button onClick={onClick} className={"button"} style={styles}>
			{buttonText}
		</button>
	);
}

export default CustomButton;
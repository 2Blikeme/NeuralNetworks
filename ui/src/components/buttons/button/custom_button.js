import React from 'react';
import './custom_button.css'

function Custom_button({
						  buttonText = '',
						  styles,
					  }) {
	return (
		<button className={"button"} style={styles}>
			{buttonText}
		</button>
	);
}

export default Custom_button;
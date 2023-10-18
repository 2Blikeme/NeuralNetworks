import React from 'react';

function CustomButton({
						  buttonText = '',
						  className
					  }) {


	return (
		<button className={className}>
			{buttonText}
		</button>
	);
}

export default CustomButton;
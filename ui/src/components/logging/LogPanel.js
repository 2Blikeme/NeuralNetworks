import React from 'react';
import './logPanel.css'

function LogPanel({props, text}) {

	return (
		<div className={"panel"}>
			{
				text
			}
		</div>
	);
}

export default LogPanel;
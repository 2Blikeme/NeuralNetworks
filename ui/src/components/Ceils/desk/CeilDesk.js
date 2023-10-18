import React, {useState} from 'react';
import CeilRow from "../row/CeilRow";
import {useSelector} from "react-redux";
import {minHeight, minWidth} from "../../../consts";

const CeilDesk = ({
					  disabled = false,
					  lowSize = false,
					  matrix,
				  }) => {
	const count = useSelector((state) => state.ceils.height)

	const rows = []
	for (let i = 0; i < count; i++) {
		rows.push(
			<CeilRow key={i}
					 rowKey={{value: i}}
					 lowSize={lowSize}
					 disabled={disabled}
			/>
		)
	}

	return (
		<div>
			{
				rows
			}
		</div>
	);
};

export default CeilDesk;
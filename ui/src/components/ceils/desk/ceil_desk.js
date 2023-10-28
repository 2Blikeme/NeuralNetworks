import React from 'react';
import Row from "../row/row";
import {useSelector} from "react-redux";
import './ceil_desk.css'

const CeilDesk = ({
					  disabled = false,
					  matrix,
				  }) => {
	const count = useSelector((state) => state.ceils.height)

	const rows = []
	for (let i = 0; i < count; i++) {
		rows.push(
			<Row key={i}
				 rowKey={{value: i}}
				 disabled={disabled}
			/>
		)
	}

	return (
		<div className={'row-container'}>
			{
				rows
			}
		</div>
	);
};

export default CeilDesk;
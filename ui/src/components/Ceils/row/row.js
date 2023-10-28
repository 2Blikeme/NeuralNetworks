import React, {useState} from 'react';
import Ceil from "../ceil/ceil";
import './row.css'
import {useSelector} from "react-redux";

const Row = ({
					 matrixVector,
					 rowKey,
					 disabled = false,
					 lowSize = false,
				 }) => {
	const length = useSelector((state) => state.ceils.width)

	const cells = []
	for (let i = 0; i < length; i++) {
		cells.push(
			<Ceil key={i}
				  ceilKey={{i: rowKey.value, j: i}}
				  lowSize={lowSize}
				  disabled={disabled}/>
		)
	}

	return (
		<div key={rowKey} className={"row"}>
			{
				cells
			}
		</div>
	);
};

export default Row;
import React, {useState} from 'react';
import Ceil from "../ceil/Ceil";
import './row.css'
import {useSelector} from "react-redux";

const CeilRow = () => {
	// const [length, setLength] = useState(10);
	const length = useSelector((state) => state.ceils.width)

	return (
		<div className={"row"}>
			{
				Array.from({length: length}).fill(
					<Ceil/>
				)
			}
		</div>
	);
};

export default CeilRow;
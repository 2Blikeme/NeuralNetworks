import React, {useState} from 'react';
import CeilRow from "../row/CeilRow";
import {useDispatch, useSelector} from "react-redux";

const CeilDesk = () => {

	const [numRows, setNumRows] = useState(3)
	const count = useSelector((state) => state.ceils.height)

	return (
		<div>
			{
				Array.from({length: count}).fill(
					<CeilRow />
				)
			}
		</div>
	);
};

export default CeilDesk;
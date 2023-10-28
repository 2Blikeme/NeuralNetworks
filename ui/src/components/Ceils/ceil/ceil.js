import React, {useState} from 'react';
import './ceil.css'
import {useDispatch} from "react-redux";
import {changeMatrix} from "../../../store/slices/ceil_slice";


const Ceil = ({
				  ceilKey,
				  disabled = false,
				  lowSize = false,
			  }) => {
	const [clicked, setClicked] = useState(false)

	const dispatch = useDispatch()

	const clickHandler = (() => {
		if (disabled) return
		dispatch(changeMatrix(
			{
				key: ceilKey,
				// если true  - белый цвет
				// если false - черный цвет
				changeColor: true,
			}))
		setClicked(!clicked)
	})

	return (
		<div key={ceilKey} className={lowSize ? "low-size-square" : "square"}
			 style={{
				 backgroundColor: clicked ? 'black' : 'white',
			 }}
			 onClick={clickHandler}>
		</div>
	);
};

export default Ceil;
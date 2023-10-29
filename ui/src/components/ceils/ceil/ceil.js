import React, {useState} from 'react';
import './ceil.css'
import {useDispatch} from "react-redux";
import {changeMatrix} from "../../../store/slices/ceil_slice";


const Ceil = ({
				  ceilKey,
				  ceilValue,
				  disabled = false,
			  }) => {

	const dispatch = useDispatch()

	const clickHandler = (() => {
		if (disabled) return
		dispatch(changeMatrix(
			{
				key: ceilKey,
				// если true  - белый цвет
				// если false - черный цвет
				changeColor: ceilValue !== 1,
			}))
	})

	return (
		<div key={ceilKey} className={"square"}
			 style={{
				 backgroundColor: ceilValue === 1 ? 'black' : 'white',
			 }}
			 onClick={clickHandler}>
		</div>
	);
};

export default Ceil;
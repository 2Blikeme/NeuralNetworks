import React, {useState} from 'react';
import './ceils.css'


const Ceil = () => {
	const [clicked, setClicked] = useState(false)

	const clickHandler = (() => {
		setClicked(!clicked)
	})

	return (
		<div className={"square"}
			 style={{backgroundColor: clicked ? 'black' : 'white'}}
			 onClick={clickHandler}>
		</div>
	);
};

export default Ceil;
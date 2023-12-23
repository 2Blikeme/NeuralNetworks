import React, {useState} from 'react';
import CeilDesk from "../ceils/desk/ceil_desk";
import './data_input.css'
import CustomButton from "../buttons/button/custom_button";
import {useDispatch, useSelector} from "react-redux";
import {clearMatrix} from "../../store/slices/ceil_slice";
import {predict, trainNetwork} from "../../services/NeuralNetworkService";
import {BINARY, BIPOLAR} from "../../consts/nnConsts";
import Select from 'react-select';
import LogPanel from "../logging/LogPanel";

const options = [
	{ value: 'BINARY', label: 'Бинарное представление',},
	{ value: 'BIPOLAR', label: 'Биполярное представление' },
]

function DataInput(props) {

	const [presentation, setPresentation] = useState(options[0])
	const [resultPredict, setResultPredict] = useState(undefined)

	const dispatch = useDispatch()
	const inputMatrix = useSelector((state) => state.ceils.matrix)

	const clearDataButtonListener = () => {
		dispatch(clearMatrix())
	}

	const trainButtonClickListener = () => {
		const metadata = {
			activationMode: presentation.value,
		}
		trainNetwork(metadata)
	}

	const predictButtonListener = () => {
		predict().then((response) => {
			setResultPredict(response.data.metadata.predictedClass)
		})
	}

	return (
		<div className={"data-input-container"}>
			<CeilDesk matrix={inputMatrix}/>
			<CustomButton onClick={clearDataButtonListener} buttonText={'Clear'}/>
			<CustomButton onClick={trainButtonClickListener} buttonText={'Train'}/>
			<CustomButton onClick={predictButtonListener} buttonText={'Predict'}/>
			<Select
				defaultValue={options[0]}
				options={options}
				onChange={setPresentation}
				isSearchable={false}
			/>
			<LogPanel text={resultPredict !== undefined ?
				`Class ${resultPredict}` :
				'Enter the letters and make a prediction'} />
		</div>
	);
}

export default DataInput;
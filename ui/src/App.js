import "./App.css"
import SavedValues from "./components/editor/saved_values";
import DataInput from "./components/data_input/data_input";

function App() {
	return (
		<div className="App">
			<div className={"app-container"}>
				<div className={"matrix-container"}>
					<DataInput/>
				</div>
				<div className={"saved-values-container"}>
					<SavedValues name={'A1'}/>
					<SavedValues name={'A2'}/>
					<SavedValues name={'B1'}/>
					<SavedValues name={'B2'}/>
					<SavedValues name={'C'}/>
				</div>

			</div>

		</div>
	);
}

export default App;

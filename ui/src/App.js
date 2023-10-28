import Ceil_desk from "./components/Ceils/desk/ceil_desk";
import "./App.css"
import SavedValues from "./components/editor/saved_values";

function App() {
	return (
		<div className="App">
			<div className={"app-container"}>
				<div className={"matrix-container"}>
					<Ceil_desk/>
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

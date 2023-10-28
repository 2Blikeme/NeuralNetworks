import Ceil_desk from "./components/Ceils/desk/ceil_desk";
import "./App.css"
import Saved_values from "./components/editor/saved_values";

function App() {
	return (
		<div className="App">
			<div className={"app-container"}>
				<div className={"matrix-container"}>
					<Ceil_desk/>
				</div>
				<div className={"saved-values-container"}>
					<Saved_values name={'A1'}/>
					<Saved_values name={'A2'}/>
					<Saved_values name={'B1'}/>
					<Saved_values name={'B2'}/>
					<Saved_values name={'C'}/>
				</div>
			</div>
		</div>
	);
}

export default App;

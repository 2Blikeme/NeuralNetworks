import CeilDesk from "./components/Ceils/desk/CeilDesk";
import "./App.css"
import Editor from "./components/editor/editor";
import SavedValues from "./components/editor/savedValues";

function App() {
	return (
		<div className="App">
			<div className={"container"}>
				<div>
					<CeilDesk/>
					<Editor/>
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

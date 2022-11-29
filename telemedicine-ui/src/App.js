import React from "react";
import Patient from "./components/Patient";
import Doctor from "./components/Doctor";
import Admin from "./components/Admin";
import Tabs from "./components/Tabs";
import "./App.css";

function App() {
  return (
    <div>
      <h1>Telemedicine Portal</h1>
      <Tabs>
        <div label="Patient">
          <Patient />
        </div>
        <div label="Doctor">
          <Doctor />
        </div>
        <div label="Admin">
          <Admin />
        </div>
      </Tabs>
    </div>
  );
}

export default App;
import React, { Component } from "react";
import Login from "./components/Login";
import Tabs from "./components/Tabs";
import "./App.css";

function App() {
  return (
    <div>
      <h1>Telemedicine Portal</h1>
      <Tabs>
        <div label="Patient">
          <Login />
        </div>
        <div label="Doctor">
          Nothing to see here, this tab is <em>empty</em>!
        </div>
        <div label="Admin">
          Nothing to see here, this tab is <em>empty</em>!
        </div>
      </Tabs>
    </div>
  );
}

export default App;
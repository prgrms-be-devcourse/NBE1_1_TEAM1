import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import React from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Login } from "./components/Login";
import MainPage from "./components/MainPage";

function App() {
    return (
        <Router>
            <div className="container-fluid">
                <div className="row justify-content-center m-4">
                    <h1 className="text-center">Grids & Circle</h1>
                </div>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/main" element={<MainPage />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
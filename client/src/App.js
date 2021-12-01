import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";

const App = () => {
    return (
        <React.Fragment>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route exact path="/login" element={<LoginPage />} />
                <Route exact path="/register" element={<RegisterPage />} />
            </Routes>
            <Routes>
                <Route exact path="/dashboard" element={<h1>cos111</h1>} />
            </Routes>
        </React.Fragment>
    );
};

export default App;

import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import Dashboard from "./components/Dashboard/Dashboard";

const App = () => {
    return (
        <React.Fragment>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route exact path="/login" element={<LoginPage />} />
                <Route exact path="/register" element={<RegisterPage />} />
                <Route exact path="/dashboard" element={<Dashboard />} />{" "}
                {/* TODO: Make dashboard component, add JWT token cookie, add registered successful alert*/}
            </Routes>
        </React.Fragment>
    );
};

export default App;

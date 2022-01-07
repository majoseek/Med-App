import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import Home from "./components/DashboardPatient/Home";
import { useState } from "react";
import HomeDoctor from "./components/DashboardDoctor/HomeDoctor";
const App = () => {
    const [isDoctor, setIsDoctor] = useState(true);
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path="/" element={<LandingPage />} />
                <Route
                    path="/login"
                    element={<LoginPage setIsDoctor={setIsDoctor} />}
                />
                <Route path="/register" element={<RegisterPage />} />
                <Route
                    path="/dashboard/*"
                    element={isDoctor ? <HomeDoctor /> : <Home />}
                />{" "}
            </Routes>
        </BrowserRouter>
    );
};

export default App;

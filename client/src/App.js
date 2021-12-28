import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import DashboardDoctor from "./components/DashboardDoctor/DashboardDoctor";
import Home from "./components/DashboardPatient/Home";
import { useEffect, useState } from "react";
const App = () => {
    const [isDoctor, setIsDoctor] = useState(false);
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path="/" element={<LandingPage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route
                    path="/dashboard/*"
                    element={isDoctor ? <DashboardDoctor /> : <Home />}
                />{" "}
            </Routes>
        </BrowserRouter>
    );
};

export default App;

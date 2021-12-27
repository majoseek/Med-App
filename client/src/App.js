import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import DashboardDoctor from "./components/DashboardDoctor/DashboardDoctor";
import DashboardPatient from "./components/DashboardPatient/DashboardPatient";
import { useEffect, useState } from "react";
const App = () => {
    const [isDoctor, setIsDoctor] = useState(false);
    return (
        <React.Fragment>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route exact path="/login" element={<LoginPage />} />
                <Route exact path="/register" element={<RegisterPage />} />
                <Route
                    exact
                    path="/dashboard"
                    element={
                        isDoctor ? <DashboardDoctor /> : <DashboardPatient />
                    }
                />{" "}
            </Routes>
        </React.Fragment>
    );
};

export default App;

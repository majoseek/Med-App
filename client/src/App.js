import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import { Navigation } from "./components/Navigation/Navigation";
import LoginPage from "./components/LoginPage/LoginPage";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";

const App = () => {
    return (
        <React.Fragment>
            <Navigation />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route exact path="/login" element={<LoginPage />} />
                <Route exact path="/register" element={<RegisterPage />} />
            </Routes>
            <div id="footer">
                <div className="container text-center">
                    <p>Medicine App</p>
                </div>
            </div>
        </React.Fragment>
    );
};

export default App;

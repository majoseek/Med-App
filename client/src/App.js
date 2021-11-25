import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import { Navigation } from "./components/Navigation/Navigation";
import LoginPage from "./components/LoginPage/LoginPage";
import "./App.css";

const App = () => {
    return (
        <React.Fragment>
            <Navigation />
            <LandingPage />
            {/* <LoginPage /> */}
            <div id="footer">
                <div className="container text-center">
                    <p>Medicine App</p>
                </div>
            </div>
        </React.Fragment>
    );
};

export default App;

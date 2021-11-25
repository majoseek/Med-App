import React from "react";
import LandingPage from "./components/LandingPage/LandingPage";
import { Navigation } from "./components/Navigation/Navigation";

const App = () => {
    return (
        <React.Fragment>
            <Navigation />
            <LandingPage />
        </React.Fragment>
    );
};

export default App;

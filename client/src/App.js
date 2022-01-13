import LandingPage from "./components/LandingPage/LandingPage";
import LoginPage from "./components/LoginPage/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import RegisterPage from "./components/RegisterPage/RegisterPage";
import Home from "./components/DashboardPatient/Home";
import HomeDoctor from "./components/DashboardDoctor/HomeDoctor";
import { CookiesProvider } from "react-cookie";
const App = () => {
    return (
        <CookiesProvider>
            <BrowserRouter>
                <Routes>
                    <Route exact path="/" element={<LandingPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/register" element={<RegisterPage />} />
                    <Route
                        path="/dashboard/doctor/*"
                        element={<HomeDoctor />}
                    />
                    <Route path="/dashboard/patient/*" element={<Home />} />
                </Routes>
            </BrowserRouter>
        </CookiesProvider>
    );
};

export default App;

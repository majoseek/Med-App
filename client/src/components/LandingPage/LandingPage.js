import { Header } from "../Header/Header";
import { Features } from "../Features/Features";
import { About } from "../About/About";
import { Services } from "../Services/Services";
import { Gallery } from "../Gallery/Gallery";
import { Testimonials } from "../Testimonials/Testimonials";
import { Team } from "../Team/Team";
import { Contact } from "../Contact/Contact";
import "./LandingPage.css";
import React, { useEffect } from "react";
import { Navigation } from "../Navigation/Navigation";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

const LandingPage = () => {
    const navigate = useNavigate();
    const [cookies, setCookie] = useCookies("access_token", "role");
    useEffect(() => {
        if (cookies.access_token) {
            if (cookies.role === "PATIENT") navigate("/dashboard/patient");
            else if (cookies.role === "DOCTOR") navigate("/dashboard/doctor");
        }
    }, [cookies.access_token, cookies.role]);
    return (
        <React.Fragment>
            <Navigation />
            <div>
                <Header />
                <Features />
                <About />
                <Services />
                <Gallery />
                <Testimonials />
                <Team />
                <Contact />
                <div id="footer">
                    <div className="container text-center">
                        <p>Medicine App</p>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};

export default LandingPage;

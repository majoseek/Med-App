import { useState, useEffect } from "react";
import { Navigation } from "../Navigation/Navigation";
import { Header } from "../Header/Header";
import { Features } from "../Features/Features";
import { About } from "../About/About";
import { Services } from "../Services/Services";
import { Gallery } from "../Gallery/Gallery";
import { Testimonials } from "../Testimonials/Testimonials";
import { Team } from "../Team/Team";
import { Contact } from "../Contact/Contact";
import JsonData from "../../data/data.json";
import SmoothScroll from "smooth-scroll";
import "./LandingPage.css";

export const scroll = new SmoothScroll('a[href*="#"]', {
    speed: 1000,
    speedAsDuration: true,
});

const LandingPage = () => {
    const [landingPageData, setLandingPageData] = useState({});
    useEffect(() => {
        setLandingPageData(JsonData);
    }, []);

    return (
        <div>
            <Navigation />
            <Header data={landingPageData.Header} />
            <Features data={landingPageData.Features} />
            <About data={landingPageData.About} />
            <Services data={landingPageData.Services} />
            <Gallery data={landingPageData.Gallery} />
            <Testimonials data={landingPageData.Testimonials} />
            <Team data={landingPageData.Team} />
            <Contact data={landingPageData.Contact} />
        </div>
    );
};

export default LandingPage;

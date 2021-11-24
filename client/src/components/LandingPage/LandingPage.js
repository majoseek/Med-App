import { Navigation } from "../Navigation/Navigation";
import { Header } from "../Header/Header";
import { Features } from "../Features/Features";
import { About } from "../About/About";
import { Services } from "../Services/Services";
import { Gallery } from "../Gallery/Gallery";
import { Testimonials } from "../Testimonials/Testimonials";
import { Team } from "../Team/Team";
import { Contact } from "../Contact/Contact";
import SmoothScroll from "smooth-scroll";
import "./LandingPage.css";

export const scroll = new SmoothScroll('a[href*="#"]', {
    speed: 1000,
    speedAsDuration: true,
});

const LandingPage = () => {
    return (
        <div>
            <Navigation />
            <Header />
            <Features />
            <About />
            <Services />
            <Gallery />
            <Testimonials />
            <Team />
            <Contact />
        </div>
    );
};

export default LandingPage;

import { Header } from "../Header/Header";
import { Features } from "../Features/Features";
import { About } from "../About/About";
import { Services } from "../Services/Services";
import { Gallery } from "../Gallery/Gallery";
import { Testimonials } from "../Testimonials/Testimonials";
import { Team } from "../Team/Team";
import { Contact } from "../Contact/Contact";
import "./LandingPage.css";

const LandingPage = () => {
    return (
        <div>
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

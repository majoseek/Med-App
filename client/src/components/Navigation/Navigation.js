import "./Navigation.css";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import { Link } from "react-router-dom";
import { Link as LinkScroll } from "react-scroll";
export const Navigation = () => {
    return (
        <Navbar bg="light" expand="lg" fixed="top">
            <Container>
                <Navbar.Brand
                    as={LinkScroll}
                    to="header"
                    spy={true}
                    smooth={true}
                    duration={50}
                >
                    PAP-MED-APP
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link
                            as={LinkScroll}
                            to="features"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Features
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="about"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            About
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="services"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Services
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="portfolio"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Gallery
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="testimonials"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Testimonials
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="team"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Team
                        </Nav.Link>
                        <Nav.Link
                            as={LinkScroll}
                            to="contact"
                            spy={true}
                            smooth={true}
                            duration={50}
                        >
                            Contact
                        </Nav.Link>
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                        <Nav.Link as={Link} to="/register">
                            Register
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

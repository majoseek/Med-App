import "./Navigation.css";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import { Link } from "react-router-dom";
import { Link as LinkScroll } from "react-scroll";
import Button from "react-bootstrap/Button";
export const Navigation = () => {
    return (
        <Navbar bg="light" expand="lg" fixed="top">
            <Container>
                <Navbar.Brand as={Link} to="/">
                    <LinkScroll
                        to="header"
                        spy={true}
                        smooth={true}
                        duration={50}
                    >
                        PAP-MED
                    </LinkScroll>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/#features">
                            <LinkScroll
                                to="features"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Features
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#about">
                            <LinkScroll
                                to="about"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                About
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#services">
                            <LinkScroll
                                to="services"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Services
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#portfolio">
                            <LinkScroll
                                to="portfolio"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Gallery
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#testimonials">
                            <LinkScroll
                                to="testimonials"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Testimonials
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#team">
                            <LinkScroll
                                to="team"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Team
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#contact">
                            <LinkScroll
                                to="contact"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            >
                                Contact
                            </LinkScroll>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/login">
                            <Button>Login</Button>
                        </Nav.Link>
                        <Nav.Link as={Link} to="/register">
                            <Button>Register</Button>
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

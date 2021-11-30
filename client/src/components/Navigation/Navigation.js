import "./Navigation.css";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import { Link } from "react-router-dom";
import Button from "react-bootstrap/Button";
import { Link as LinkScroll } from "react-scroll";
export const Navigation = () => {
    return (
        <Navbar bg="light" expand="lg" fixed="top">
            <Container>
                <Navbar.Brand>
                    <Link to="/">PAP-APP</Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/#features">
                            {/* <LinkScroll
                                to="features"
                                spy={true}
                                smooth={true}
                                duration={50}
                                className="btn btn-primary"
                            > */}
                            Features
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#about">
                            About
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#services">
                            Services
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#portfolio">
                            Gallery
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#testimonials">
                            Testimonials
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#team">
                            Team
                        </Nav.Link>
                        <Nav.Link as={Link} to="/#contact">
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

import "./Header.css";
import { Link } from "react-scroll";
export const Header = () => {
    return (
        <header id="header">
            <div className="intro">
                <div className="overlay">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-6 offset-md-2 offset-lg-0 intro-text">
                                <h1>
                                    Welcome!
                                    <span></span>
                                </h1>
                                <p>
                                    They say laughter is the best medicine, well
                                    it is more like a medicine a general
                                    physician will prescribe but if you want
                                    specialized care, make sure to visit us.
                                </p>
                                <Link
                                    to="features"
                                    activeClass="active"
                                    spy={true}
                                    smooth={true}
                                    duration={50}
                                    className="btn btn-primary"
                                >
                                    Learn More
                                </Link>{" "}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

import "./Navigation.css";
export const Navigation = () => {
    return (
        <nav id="menu" className="navbar navbar-default fixed-top">
            <div className="container">
                <div>
                    <button
                        type="button"
                        className="navbar-toggler"
                        data-toggle="collapse"
                        data-target="#menu_collapse"
                    >
                        {" "}
                        <span className="d-md-none">
                            Toggle navigation
                        </span>{" "}
                        <span className="icon-bar"></span>{" "}
                        <span className="icon-bar"></span>{" "}
                        <span className="icon-bar"></span>{" "}
                    </button>
                    <a className="navbar-brand page-scroll" href="#page-top">
                        React Landing Page
                    </a>{" "}
                </div>

                <div className="collapse navbar-collapse" id="menu_collapse">
                    <ul className="nav navbar-nav navbar-right">
                        <li>
                            <a href="#features" className="page-scroll">
                                Features
                            </a>
                        </li>
                        <li>
                            <a href="#about" className="page-scroll">
                                About
                            </a>
                        </li>
                        <li>
                            <a href="#services" className="page-scroll">
                                Services
                            </a>
                        </li>
                        <li>
                            <a href="#portfolio" className="page-scroll">
                                Gallery
                            </a>
                        </li>
                        <li>
                            <a href="#testimonials" className="page-scroll">
                                Testimonials
                            </a>
                        </li>
                        <li>
                            <a href="#team" className="page-scroll">
                                Team
                            </a>
                        </li>
                        <li>
                            <a href="#contact" className="page-scroll">
                                Contact
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

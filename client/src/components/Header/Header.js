import "./Header.css";
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
                                <a href="#features" className="btn btn-primary">
                                    Learn More
                                </a>{" "}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

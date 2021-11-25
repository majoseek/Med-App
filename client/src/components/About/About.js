import "./About.css";
import AboutImg from "./about.jpg";
export const About = () => {
    return (
        <div id="about">
            <div className="container">
                <div className="row">
                    <div className="col-12 col-md-6">
                        {" "}
                        <img
                            src={AboutImg}
                            className="img-responsive"
                            alt=""
                        />{" "}
                    </div>
                    <div className="col-12 col-md-6">
                        <div className="about-text">
                            <h2>About Us</h2>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut
                                enim ad minim veniam, quis nostrud exercitation
                                ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Lorem ipsum dolor sit amet,
                                consectetur adipiscing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna
                                aliqua.
                            </p>
                            <h3>Why Choose Us?</h3>
                            <div className="list-style">
                                <div className="col-lg-6 col-sm-6 col-12">
                                    <ul>
                                        <li>Cos tam</li>
                                        <li>Cos 123</li>
                                        <li>Cos</li>
                                        <li>Cos 56</li>
                                        <li>Cos 4521</li>
                                        <li>Cos 123</li>
                                    </ul>
                                </div>
                                <div className="col-lg-6 col-sm-6 col-12">
                                    <ul>
                                        <li>Cos tam</li>
                                        <li>Cos 123</li>
                                        <li>Cos</li>
                                        <li>Cos 56</li>
                                        <li>Cos 4521</li>
                                        <li>Cos 123</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

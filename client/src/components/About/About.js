import "./About.css";
import AboutImg from "./about.jpg";
export const About = () => {
    return (
        <div id="about" className="text-center">
            <div className="container">
                <div className="row section-title">
                    <h2>About Us</h2>
                </div>
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
                            <p>
                                Our group offers variety of medical care
                                possibilities. From diagnostic, through
                                occupational medicine, up to internist
                                consultation and covid tests.
                            </p>
                            <h3>Why Choose Us?</h3>
                            <div className="row">
                                <div className="col-lg-6 col-sm-6 col-12">
                                    <ul>
                                        <li>no queues</li>
                                        <li>low cost</li>
                                        <li>only vaccinated doctors</li>
                                        <li>affordable insurance policy</li>
                                    </ul>
                                </div>
                                <div className="col-lg-6 col-sm-6 col-12">
                                    <ul>
                                        <li>many objects</li>
                                        <li>professional care</li>
                                        <li>reliability</li>
                                        <li>no bullshit</li>
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

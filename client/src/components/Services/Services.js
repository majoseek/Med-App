import "./Services.css";
export const Services = () => {
    return (
        <div id="services" className="text-center">
            <div className="container">
                <div className="section-title">
                    <h2>Our Services</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit
                        duis sed dapibus leonec.
                    </p>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-group"></i>
                        <div className="service-desc">
                            <h3>Tytul123</h3>
                            <p>
                                Lorem ipsum dolor sit amet placerat facilisis
                                felis mi in{" "}
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-magic"></i>
                        <div className="service-desc">
                            <h3>Tytul123</h3>
                            <p>
                                Lorem ipsum dolor sit amet placerat facilisis
                                felis mi in{" "}
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-bullhorn"></i>
                        <div className="service-desc">
                            <h3>Tytul453</h3>
                            <p>
                                Lorem ipsum dolor sit amet placerat facilisis
                                felis mi in{" "}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

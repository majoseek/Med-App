import "./Services.css";
export const Services = () => {
    return (
        <div id="services" className="text-center">
            <div className="container">
                <div className="section-title">
                    <h2>Our Services</h2>
                    <p>We live to serve.</p>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-group"></i>
                        <div className="service-desc">
                            <h3>Diagnostic</h3>
                            <p>
                                We are ready to examine your problems and give
                                you best healthcare possible.{" "}
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-magic"></i>
                        <div className="service-desc">
                            <h3>Care</h3>
                            <p>
                                Professional modern facilities with air
                                conditioning will make you cool any time of the
                                year.{" "}
                            </p>
                        </div>
                    </div>
                    <div className="col-md-4">
                        {" "}
                        <i className="fa fa-bullhorn"></i>
                        <div className="service-desc">
                            <h3>Consultation</h3>
                            <p>
                                Specialists eager to give your codeine
                                prescription.{" "}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

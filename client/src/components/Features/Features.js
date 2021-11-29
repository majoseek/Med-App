import "./Features.css";
export const Features = () => {
    return (
        <div id="features" className="text-center">
            <div className="container">
                <div className="row section-title">
                    <h2>Features</h2>
                </div>
                <div className="row">
                    <div className="col-6 col-md-3">
                        {" "}
                        <i className="fa fa-glass"></i>
                        <h4>Online consultations</h4>
                        <p>
                            You dont need to leave your home. Just fill in the
                            forms and register your express online visit right
                            away.
                        </p>
                    </div>
                    <div className="col-6 col-md-3">
                        {" "}
                        <i className="fa fa-bullhorn"></i>
                        <h4 className="font-weight-bold">
                            Periodic examinations
                        </h4>
                        <p>
                            Dont waste your time waiting in the never ending
                            queue, it is your health, you have to be serious
                            about it.
                        </p>
                    </div>
                    <div className="col-6 col-md-3">
                        {" "}
                        <i className="fa fa-magic"></i>
                        <h4>Emergency help</h4>
                        <p>We offer 24/7 er in any of our establishments.</p>
                    </div>
                    <div className="col-6 col-md-3">
                        {" "}
                        <i className="fa fa-group"></i>
                        <h4>Disabled facilities</h4>
                        <p>
                            People with disabilities are offered special
                            treatment. Our facilities feature elevators and free
                            wheelchairs.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

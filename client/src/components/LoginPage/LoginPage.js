import { useState } from "react";
import "./LoginPage.css";
import axios from "axios";
export default function LoginPage() {
    const [email, set_email] = useState("");
    const [password, set_password] = useState("");
    const [remember_me, set_remember] = useState(true);
    function sign_in() {
        console.log(email, password);
        axios
            .post("/users/login", {
                email: email,
                password: password,
            })
            .then((response) => console.log(response))
            .catch((error) => console.log(error.response.status));
    }
    return (
        <div className="maincontainer">
            <div className="container-fluid">
                <div className="row no-gutter">
                    <div className="col-md-6 d-none d-md-flex bg-image"></div>

                    <div className="col-md-6 bg-light">
                        <div className="login d-flex align-items-center py-5">
                            <div className="container">
                                <div className="row">
                                    <div className="col-lg-10 col-xl-7 mx-auto">
                                        <h3 className="display-4">
                                            Welcome again!
                                        </h3>
                                        <p className="text-muted mb-4">
                                            Fill in this form to sign in to your
                                            dashboard
                                        </p>
                                        <form>
                                            <div className="mb-3">
                                                <input
                                                    id="inputEmail"
                                                    type="email"
                                                    placeholder="Email address"
                                                    required=""
                                                    autoFocus={true}
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputPassword"
                                                    type="password"
                                                    placeholder="Password"
                                                    required=""
                                                    className="form-control rounded-pill border-0 shadow-sm px-4 text-primary"
                                                />
                                            </div>
                                            <div className="form-check">
                                                <input
                                                    id="customCheck1"
                                                    type="checkbox"
                                                    checked={remember_me}
                                                    onClick={() =>
                                                        set_remember(
                                                            !remember_me
                                                        )
                                                    }
                                                    onChange={() => {}}
                                                    className="form-check-input"
                                                />
                                                <label
                                                    htmlFor="customCheck1"
                                                    className="form-check-label"
                                                >
                                                    Remember password
                                                </label>
                                            </div>
                                            <div className="d-grid gap-2 mt-2">
                                                <button
                                                    type="submit"
                                                    className="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm"
                                                >
                                                    Sign in
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

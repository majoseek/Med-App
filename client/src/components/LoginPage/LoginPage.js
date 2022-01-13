import { useState } from "react";
import "./LoginPage.css";
import axios from "axios";
import { Link } from "react-router-dom";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
export default function LoginPage() {
    const [email, set_email] = useState("");
    const [password, set_password] = useState("");
    const [remember_me, set_remember] = useState(true);
    const [cookies, setCookie] = useCookies(["access_token"]);
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
    function sign_in() {
        setIsLoading(true);
        axios
            .post(
                "/users/login",
                new URLSearchParams({
                    email: email,
                    password: password,
                })
            )
            .then((response) => {
                setCookie("role", response.data.role);
                setCookie("access_token", response.data.access_token);
                if (response.data.role === "PATIENT")
                    navigate("/dashboard/patient");
                else navigate("/dashboard/doctor");
            })
            .catch((err) => {
                console.log("Login failed", err);
                setIsLoading(false);
            });
    }
    return (
        <div className="maincontainer">
            <div className="container-fluid">
                <div className="row no-gutter">
                    <div className="col-md-6 d-none d-md-flex bg_login-image"></div>

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
                                        <div className="justify-content-center">
                                            <div className="mb-3">
                                                <input
                                                    id="inputEmail"
                                                    type="email"
                                                    placeholder="Email address"
                                                    required=""
                                                    autoFocus={true}
                                                    value={email}
                                                    onChange={(e) =>
                                                        set_email(
                                                            e.target.value
                                                        )
                                                    }
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputPassword"
                                                    type="password"
                                                    placeholder="Password"
                                                    required=""
                                                    value={password}
                                                    onChange={(e) =>
                                                        set_password(
                                                            e.target.value
                                                        )
                                                    }
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
                                                    style={{ fontSize: "13px" }}
                                                >
                                                    Remember password
                                                </label>
                                            </div>
                                            <div className="d-grid gap-2 mt-2 m-auto w-75 text-center">
                                                {isLoading ? (
                                                    <CircularProgress />
                                                ) : (
                                                    <button
                                                        className="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm"
                                                        onClick={() =>
                                                            sign_in()
                                                        }
                                                    >
                                                        Sign in
                                                    </button>
                                                )}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <Link to="/" style={{ textDecoration: "none" }}>
                                    <div
                                        className="row text-center mt-5"
                                        style={{ cursor: "pointer" }}
                                    >
                                        <div className="col m-0">
                                            <div className="row">
                                                <div className="col">
                                                    <i
                                                        className="fa fa-arrow-left left_arr"
                                                        aria-hidden="true"
                                                    ></i>
                                                </div>
                                            </div>
                                            <div className="row mt-2">
                                                <div className="col back_home">
                                                    Back home
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

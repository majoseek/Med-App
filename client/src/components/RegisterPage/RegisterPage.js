import { useState } from "react";
import "./RegisterPage.css";
import axios from "axios";
import CircularProgress from "@mui/material/CircularProgress";
import { Link, useNavigate } from "react-router-dom";
export default function RegisterPage() {
    const [email, set_email] = useState("");
    const [name, set_name] = useState("");
    const [surname, set_surname] = useState("");
    const [pesel, set_pesel] = useState("");
    const [password, set_password] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
    function register() {
        setIsLoading(true);
        axios
            .post(
                "/users/create/patient",
                new URLSearchParams({
                    email: email,
                    name: name,
                    surname: surname,
                    pesel: pesel,
                    password: password,
                })
            )
            .then((result) => {
                console.log(result);
                navigate("/login");
            })
            .catch((err) => {
                console.log("REGISTER FAILED");
                console.log(err);
            })
            .finally(setIsLoading(false));
    }
    return (
        <div className="maincontainer">
            <div className="container-fluid">
                <div className="row no-gutter">
                    <div className="col-md-6 d-none d-md-flex bg-image_register"></div>

                    <div className="col-md-6 bg-light">
                        <div className="register d-flex align-items-center py-5">
                            <div className="container">
                                <div className="row">
                                    <div className="col-lg-10 col-xl-7 mx-auto">
                                        <h3 className="display-4">
                                            Nice to meet you!
                                        </h3>
                                        <p className="text-muted mb-4">
                                            Fill in this form to register your
                                            account
                                        </p>
                                        <div className="justify-content-center">
                                            <div className="mb-3">
                                                <input
                                                    id="inputEmail"
                                                    type="email"
                                                    placeholder="Email address"
                                                    required={true}
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
                                                    id="inputName"
                                                    type="text"
                                                    placeholder="Name"
                                                    required={true}
                                                    value={name}
                                                    onChange={(e) =>
                                                        set_name(e.target.value)
                                                    }
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputSurName"
                                                    type="text"
                                                    placeholder="Surname"
                                                    required={true}
                                                    value={surname}
                                                    onChange={(e) =>
                                                        set_surname(
                                                            e.target.value
                                                        )
                                                    }
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputPesel"
                                                    type="text"
                                                    placeholder="Pesel"
                                                    required={true}
                                                    value={pesel}
                                                    onChange={(e) =>
                                                        set_pesel(
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
                                                    required={true}
                                                    value={password}
                                                    onChange={(e) =>
                                                        set_password(
                                                            e.target.value
                                                        )
                                                    }
                                                    className="form-control rounded-pill border-0 shadow-sm px-4 text-primary"
                                                />
                                            </div>
                                            <div className="d-grid gap-2 mt-2 w-75 m-auto text-center">
                                                {isLoading ? (
                                                    <CircularProgress />
                                                ) : (
                                                    <button
                                                        onClick={() =>
                                                            register()
                                                        }
                                                        className="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm"
                                                    >
                                                        Register
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

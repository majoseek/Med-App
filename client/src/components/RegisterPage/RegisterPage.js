import { useState } from "react";
import "./RegisterPage.css";
import axios from "axios";
import ArrowLeft from "./arrow-left.svg";
export default function RegisterPage() {
    const [email, set_email] = useState("");
    const [password, set_password] = useState("");
    function register() {
        console.log(email, password);
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
                                        <form className="justify-content-center">
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
                                                    id="inputName"
                                                    type="text"
                                                    placeholder="Name"
                                                    required=""
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputSurName"
                                                    type="text"
                                                    placeholder="Surname"
                                                    required=""
                                                    className="form-control rounded-pill border-0 shadow-sm px-4"
                                                />
                                            </div>
                                            <div className="mb-3">
                                                <input
                                                    id="inputPesel"
                                                    type="text"
                                                    placeholder="Pesel"
                                                    required=""
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
                                            <div className="d-grid gap-2 mt-2 w-75 m-auto">
                                                <button
                                                    type="submit"
                                                    className="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm"
                                                >
                                                    Register
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div className="row text-center mt-5">
                                    <div className="col m-0">
                                        <div
                                            className="row"
                                            style={{ cursor: "pointer" }}
                                        >
                                            <div className="col">
                                                <img
                                                    src={ArrowLeft}
                                                    alt="Back home"
                                                    width="32px"
                                                    height="32px"
                                                />
                                            </div>
                                        </div>
                                        <div
                                            className="row mt-2"
                                            style={{ cursor: "pointer" }}
                                        >
                                            <div className="col">Back home</div>
                                        </div>
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

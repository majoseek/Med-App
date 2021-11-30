import { useState } from "react";
import { Button } from "react-bootstrap";
import "./LoginPage.css";
import axios from "axios";
export default function LoginPage() {
    const [email, set_email] = useState("");
    const [password, set_password] = useState("");
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
        <div className="container main_div text-center">
            <div className="row">
                <div className="col-md-5">
                    <h3>Login</h3>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => set_email(e.target.value)}
                    />
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => set_password(e.target.value)}
                    />
                    <Button onClick={() => sign_in()}>Submit</Button>
                </div>
                <div className="col-md-5">
                    <h1>cos</h1>
                </div>
            </div>
        </div>
    );
}

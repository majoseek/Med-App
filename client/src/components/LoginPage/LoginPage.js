import { useState } from "react";
import { Button } from "react-bootstrap";
import "./LoginPage.css";
export default function LoginPage() {
    const [login, set_login] = useState("");
    const [password, set_password] = useState("");
    function sign_in() {
        console.log(login, password);
        //axios send request
    }
    return (
        <div className="container main_div text-center">
            <div className="row">
                <div className="col-md-5">
                    <h3>Login</h3>
                    <input
                        type="text"
                        value={login}
                        onChange={(e) => set_login(e.target.value)}
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

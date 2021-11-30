import Button from "react-bootstrap/Button";
export default function RegisterPage() {
    function register() {}
    return (
        <div className="container main_div text-center">
            <div className="row">
                <div className="col-md-5">
                    <h3>Register</h3>
                    <input type="text" />
                    <input type="password" />
                    <Button onClick={() => register()}>Submit</Button>
                </div>
                <div className="col-md-5">
                    <h1>cos</h1>
                </div>
            </div>
        </div>
    );
}

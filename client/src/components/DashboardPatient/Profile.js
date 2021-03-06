import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Title from "./Title";
import { TextField } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";
export default function Profile() {
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [cookies, setCookie] = useCookies(["access_token"]);
    useEffect(() => {
        axios
            .get("/users/myData", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setEmail(result.data.email);
                setName(result.data.name);
                setSurname(result.data.surname);
            });
    }, []);
    return (
        <Grid container spacing={3}>
            <Grid item xs={12}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "center",
                        alignItems: "center",
                        "& .MuiTextField-root": { m: 1, width: "25ch" },
                    }}
                >
                    <Title>My profile</Title>
                    <Grid
                        container
                        spacing={3}
                        direction="row"
                        alignItems="center"
                        justifyContent="center"
                    >
                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="Name"
                                value={name}
                                variant="standard"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="Surname"
                                value={surname}
                                variant="standard"
                            />
                        </Grid>
                    </Grid>
                    <Grid
                        container
                        spacing={3}
                        direction="row"
                        alignItems="center"
                        justifyContent="center"
                    >
                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="E-mail"
                                value={email}
                                variant="standard"
                            />
                        </Grid>
                    </Grid>
                </Paper>
            </Grid>
        </Grid>
    );
}

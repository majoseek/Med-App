import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Title from "./Title";
import { TextField } from "@mui/material";
export default function Profile() {
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
                                defaultValue="Jan"
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="standard"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="Surname"
                                defaultValue="Kowalski"
                                InputProps={{
                                    readOnly: true,
                                }}
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
                                label="Pesel"
                                defaultValue="1234567890"
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="standard"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="E-mail"
                                defaultValue="cos.tam@gmail.com"
                                InputProps={{
                                    readOnly: true,
                                }}
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
                                label="Specialization"
                                defaultValue="Cardiology"
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="standard"
                            />
                        </Grid>
                    </Grid>
                </Paper>
            </Grid>
        </Grid>
    );
}

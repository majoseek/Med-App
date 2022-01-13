import { Grid, Paper, TextField } from "@mui/material";
import { useState } from "react";
import Title from "./Title";
import DateFnsUtils from "@date-io/date-fns"; // choose your lib
import { DatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import { Button } from "@mui/material";
import React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import axios from "axios";
import TableRow from "@mui/material/TableRow";
import { useCookies } from "react-cookie";

export default function NewVisit() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [spec, setSpec] = useState("");
    const [cookies, setCookie] = useCookies("access_token");
    const [visits, setVisits] = useState([]);
    function add_visit(id, date) {
        axios.post(
            "/visits/create",
            {
                date: "2022-04-14T02:20:00",
                description: "Wizyta",
                doctor_id: id,
                location: "103",
            },
            {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            }
        );
    }
    function show_visits() {
        const resultDate = `${startDate.getFullYear()}-${(
            "0" +
            startDate.getMonth() +
            1
        ).slice(-2)}-${startDate.getUTCDate()}T08:00:00`;
        const resultDate2 = `${endDate.getFullYear()}-${(
            "0" +
            endDate.getMonth() +
            1
        ).slice(-2)}-${endDate.getUTCDate()}T08:00:00`;
        axios
            .get(
                `/available?startDate=${resultDate}&endDate=${resultDate2}&spec=${spec}`,
                { headers: { Authorization: `Bearer ${cookies.access_token}` } }
            )
            .then((result) => {
                setVisits(result.data);
            });
    }
    return (
        <React.Fragment>
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
                        <Title>New visit</Title>

                        <Grid item>
                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                <DatePicker
                                    value={startDate}
                                    onChange={(val) => setStartDate(val)}
                                />
                            </MuiPickersUtilsProvider>
                        </Grid>
                        <Grid item>
                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                <DatePicker
                                    value={endDate}
                                    onChange={(val) => setEndDate(val)}
                                />
                            </MuiPickersUtilsProvider>
                        </Grid>

                        <Grid item>
                            <TextField
                                id="standard-read-only-input"
                                label="Specialization"
                                value={spec}
                                onChange={(e) => {
                                    setSpec(e.target.value);
                                }}
                                variant="standard"
                            />
                        </Grid>
                        <Grid item>
                            <Button
                                variant="contained"
                                onClick={() => show_visits()}
                            >
                                Show avaible visits
                            </Button>
                        </Grid>
                    </Paper>
                </Grid>
            </Grid>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Paper
                        sx={{
                            p: 2,
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
                            alignItems: "center",
                        }}
                    >
                        <Title>Avaible visits</Title>
                        <Table size="small">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Doctor</TableCell>
                                    <TableCell>Date</TableCell>
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {visits.map((row, index) => (
                                    <TableRow
                                        key={`vis_${
                                            row.id
                                        }_${row.date.toString()}`}
                                    >
                                        <TableCell>
                                            {row.doctorName} {row.doctorSurname}
                                        </TableCell>
                                        <TableCell>{row.date}</TableCell>
                                        <TableCell align="right">
                                            <button
                                                className="btn btn-primary text-uppercase rounded-pill"
                                                style={{ fontSize: "14px" }}
                                                onClick={() =>
                                                    add_visit(row.id, row.date)
                                                }
                                            >
                                                Add visit
                                            </button>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </Paper>
                </Grid>
            </Grid>
        </React.Fragment>
    );
}

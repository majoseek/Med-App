import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import axios from "axios";
import { useEffect, useState } from "react";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import Stats from "./Stats";
import Visits from "./Visits";
const data = [
    {
        id: 1,
        date: "2020-12-09",
        patient_name: "Jan Kowalski",
        visit_len: "30",
        price: 50,
    },
    {
        id: 2,
        date: "2020-12-10",
        patient_name: "Adam Kowalski",
        visit_len: "60",
        price: 100,
    },
];
export default function DashboardPatient() {
    const [cookies, setCookie] = useCookies("access_token");
    const [nextVisit, setNextVisit] = useState("YYYY-MM-DD");
    const [myVisits, setMyVisits] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        axios
            .get("/nextVisit/1", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                //setNextVisit(result.data);
                console.log(result.data);
            })
            .catch((err) => {
                console.log(cookies.access_token);
            });
        axios
            .get("/myVisits", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                console.log(result); //TODO
            });
    }, [cookies.access_token]);
    return (
        <Grid container spacing={3}>
            <Grid item xs={12} md={8} lg={9}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                    }}
                >
                    <Visits data={data} />
                </Paper>
            </Grid>
            <Grid item xs={12} md={4} lg={3}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                        justifyItems: "center",
                        alignItems: "center",
                        height: 240,
                    }}
                >
                    <Stats next_visit_date={nextVisit} />
                </Paper>
            </Grid>
        </Grid>
    );
}

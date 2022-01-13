import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import axios from "axios";
import { useEffect, useState } from "react";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import Stats from "./Stats";
import Visits from "./Visits";
export default function DashboardPatient() {
    const [cookies, setCookie] = useCookies("access_token");
    const [nextVisit, setNextVisit] = useState(null);
    const [myVisits, setMyVisits] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        axios
            .get("/nextVisit/1", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setNextVisit(result.data);
            });
        axios
            .get("/myVisits", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setMyVisits(result.data);
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
                    <Visits data={myVisits} />
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

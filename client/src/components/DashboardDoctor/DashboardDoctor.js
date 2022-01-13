import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Stats from "./Stats";
import Patients from "./Patients";
import {
    LineChart,
    Line,
    CartesianGrid,
    XAxis,
    YAxis,
    ResponsiveContainer,
} from "recharts";
import Title from "./Title";
import { useEffect, useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";
const month_patients_number = [52, 63, 72, 40, 30, 51, 60, 60, 80, 79, 65, 52];
const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "June",
    "July",
    "Aug",
    "Sept",
    "Oct",
    "Nov",
    "Dec",
];
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
export default function DashboardDoctor() {
    const [chartData, setChartData] = useState([]);
    const [myVisits, setMyVisits] = useState([]);
    const [numberOfPatients, setNumberOfPatients] = useState(0);
    const [cookies, setCookie] = useCookies(["access_token"]);
    useEffect(() => {
        setChartData(
            month_patients_number.map((num, index) => {
                return { month: months[index], number: num };
            })
        );
        axios
            .get("/myVisits", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setMyVisits(result.data);
            });
        axios
            .get("/doctor/countVisits", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setNumberOfPatients(result.data);
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
                    <Title>Number of patients</Title>
                    <ResponsiveContainer width="100%" height={400}>
                        <LineChart data={chartData}>
                            <Line
                                type="monotone"
                                dataKey="number"
                                stroke="#8884d8"
                            />
                            <CartesianGrid stroke="#ccc" />
                            <XAxis dataKey="month" />
                            <YAxis />
                        </LineChart>
                    </ResponsiveContainer>
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
                    <Stats numberOfPatients={numberOfPatients} />
                </Paper>
            </Grid>
            <Grid item xs={12}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                    }}
                >
                    <Patients data={myVisits} />
                </Paper>
            </Grid>
        </Grid>
    );
}

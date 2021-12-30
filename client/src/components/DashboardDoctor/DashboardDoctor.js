import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Stats from "./Stats";
import Patients from "./Patients";
import { LineChart, Line, CartesianGrid, XAxis, YAxis } from "recharts";
import Title from "./Title";
const data_chart = [
    { name: "Page A", uv: 400 },
    { name: "Page B", uv: 600 },
    { name: "Page C", uv: 100 },
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
                    <Title>My earnings</Title>
                    <LineChart width={600} height={300} data={data_chart}>
                        <Line type="monotone" dataKey="uv" stroke="#8884d8" />
                        <CartesianGrid stroke="#ccc" />
                        <XAxis dataKey="name" />
                        <YAxis />
                    </LineChart>
                </Paper>
            </Grid>
            <Grid item xs={12} md={4} lg={3}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                        height: 240,
                    }}
                >
                    <Stats earnings={3000} />
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
                    <Patients data={data} />
                </Paper>
            </Grid>
        </Grid>
    );
}

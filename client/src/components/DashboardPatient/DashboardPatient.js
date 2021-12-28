import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
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
    return (
        <Grid container spacing={3}>
            <Grid item xs={12} md={8} lg={9}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                        height: 240,
                    }}
                >
                    {/* TODO: add chart*/}
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
                    <Visits data={data} />
                </Paper>
            </Grid>
        </Grid>
    );
}

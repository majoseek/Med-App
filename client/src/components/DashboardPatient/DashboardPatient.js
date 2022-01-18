import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Stats from "./Stats";
import Visits from "./Visits";
export default function DashboardPatient() {

    return (
        <Grid container spacing={3}>
            <Grid item lg={12} xl={9}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                    }}
                >
                    <Visits />
                </Paper>
            </Grid>
            <Grid item lg={12} xl={3}>
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
                    <Stats />
                </Paper>
            </Grid>
        </Grid>
    );
}

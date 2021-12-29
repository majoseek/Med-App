import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
export default function Prescription() {
    return (
        <Grid container spacing={3}>
            <Grid item xs={12}>
                <Paper
                    sx={{
                        p: 2,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        justifyContent: "center",
                    }}
                >
                    <h1>Prescription (?)</h1>
                </Paper>
            </Grid>
        </Grid>
    );
}

import { makeStyles } from "@mui/styles";
import { Grid, Typography } from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material";
import { ReactComponent as Layer } from "./assets/layer1.svg";
const useStyles = makeStyles({
    root: {
        minHeight: "100vh",
        minWidth: "100vw",
        backgroundSize: "cover",
        backgroundColor: "#202731",
    },
});
const theme = createTheme();

theme.typography.h2 = {
    fontSize: "0.9rem",
    "@media (min-width:600px)": {
        fontSize: "1.8rem",
    },
    [theme.breakpoints.up("md")]: {
        fontSize: "2.6rem",
    },
};
theme.typography.h5 = {
    fontSize: "0.6rem",
    "@media (min-width:600px)": {
        fontSize: "0.9rem",
    },
    [theme.breakpoints.up("md")]: {
        fontSize: "1.5rem",
    },
};
function App() {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <ThemeProvider theme={theme}>
                <Grid
                    container
                    direction="column"
                    justifyContent="center"
                    alignItems="center"
                >
                    <Grid
                        container
                        item
                        xs={12}
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                    >
                        <Grid item>
                            <Typography
                                variant="h2"
                                align="center"
                                gutterBottom
                                fontWeight="medium"
                                color="white"
                            >
                                Medicine App
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography
                                variant="h5"
                                align="center"
                                color="white"
                            >
                                Lorem ipsum dolor sit amet, consectetur <br />
                                adipiscing elit, sed do eiusmod tempor
                            </Typography>
                        </Grid>
                    </Grid>
                    <Grid item>
                        <Layer />
                    </Grid>
                </Grid>
            </ThemeProvider>
        </div>
    );
}

export default App;

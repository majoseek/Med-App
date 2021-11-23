import { makeStyles } from "@mui/styles";
import NavBar from "./components/NavBar";
import { ReactComponent as Layer1 } from "./assets/layer1.svg";
import React from "react";
import Header from "./components/Header";
import NavCard from "./components/NavCard";
import { Grid } from "@mui/material";
import { useRef } from "react";
import WaveDivider from "./components/WaveDivider";
import Footer from "./components/Footer";
import "./components/style.css";

const useStyles = makeStyles({
    root: {
        display: "flex",
        flexDirection: "column",
    },
    layer: {
        width: "100vw",
    },
});
function App() {
    const scrollToDiv = (ref) =>
        ref.current.scrollIntoView({ behavior: "smooth", block: "start" });
    const second_layout = useRef();
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <NavBar />
            <Layer1 className={classes.layer} />
            <Header click_arrow={() => scrollToDiv(second_layout)} />
            <WaveDivider />
            <div style={{ display: "block", marginBottom: "100vh" }}></div>{" "}
            {/** divide wave from NavCards*/}
            <Grid
                container
                ref={second_layout}
                columnSpacing={{ xs: 3, sm: 6, md: 12 }}
                justifyContent="center"
            >
                <Grid item>
                    <NavCard header_text="doctor" />
                </Grid>
                <Grid item>
                    {" "}
                    <NavCard header_text="patient" />
                </Grid>
            </Grid>
            <Footer />
        </div>
    );
}

export default App;

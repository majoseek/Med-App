import { makeStyles } from "@mui/styles";
import NavBar from "./components/NavBar";
import { ReactComponent as Layer1 } from "./assets/layer1.svg";
import React from "react";
const useStyles = makeStyles({
    root: {
        backgroundSize: "cover",
        backgroundColor: "#202731",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
    },
    layer: {
        minWidth: "100vw",
    },
});
function App() {
    const classes = useStyles();
    return (
        <div>
            <NavBar />
            <Layer1 className={classes.layer} />
        </div>
    );
}

export default App;

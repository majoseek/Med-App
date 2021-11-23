import React from "react";
import Box from "@mui/material/Box";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";
import { IconButton, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
const useStyles = makeStyles({
    header_label: {
        color: "white",
    },
});

export default function Header(props) {
    const classes = useStyles();
    return (
        <Box
            sx={{
                width: "100vw",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                marginBottom: "20vh",
            }}
            onClick={props.click_arrow}
        >
            <Typography variant="h4" className={classes.header_label}>
                Get Started
            </Typography>
            <IconButton size="large">
                <ArrowDownwardIcon htmlColor="white" />
            </IconButton>
        </Box>
    );
}

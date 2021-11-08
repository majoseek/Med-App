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
        >
            <Typography
                variant="h4"
                className={classes.header_label}
                onClick={props.click_arrow}
            >
                Get Started
            </Typography>
            <IconButton size="large" onClick={props.click_arrow}>
                <ArrowDownwardIcon htmlColor="white" />
            </IconButton>
        </Box>
    );
}

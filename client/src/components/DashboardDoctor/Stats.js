import * as React from "react";
import Typography from "@mui/material/Typography";
import Title from "./Title";
import PropTypes from "prop-types";

const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
];
function getCurrentMonthName() {
    return monthNames[new Date().getMonth()];
}
export default function Stats({ earnings }) {
    return (
        <React.Fragment>
            <Title>{getCurrentMonthName()} earnings</Title>
            <Typography component="p" variant="h4">
                {earnings}$
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                since 1st {getCurrentMonthName()}, {new Date().getFullYear()}
            </Typography>
        </React.Fragment>
    );
}
Stats.propTypes = {
    earnings: PropTypes.number,
};

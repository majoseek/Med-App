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
export default function Stats({ next_visit_date }) {
    return (
        <React.Fragment>
            <Title>Next visit</Title>
            <Typography component="p" variant="h4">
                {next_visit_date}
            </Typography>
            <Typography color="text.secondary" sx={{ flex: 1 }}>
                Doctor Jan Kowalski
            </Typography>
        </React.Fragment>
    );
}
Stats.propTypes = {
    earnings: PropTypes.number,
};

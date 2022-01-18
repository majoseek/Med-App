import * as React from "react";
import Typography from "@mui/material/Typography";
import Title from "./Title";

export default function Stats({ next_visit_date }) {
    return (
        <React.Fragment>
            <Title>Next visit</Title>
            <Typography component="p" variant="h5">
                {next_visit_date === null ||
                next_visit_date === undefined ||
                next_visit_date === 0
                    ? "No upcoming visits"
                    : `${new Date(next_visit_date.visitDate).getFullYear()}-${(
                          "0" +
                          (new Date(next_visit_date.visitDate).getMonth() + 1)
                      ).slice(-2)}-${new Date(
                          next_visit_date.visitDate
                      ).getUTCDate()}`}
            </Typography>
            {next_visit_date !== null &&
            next_visit_date !== undefined &&
            next_visit_date !== 0 ? (
                <React.Fragment>
                    <Typography color="text.secondary" sx={{ flex: 1 }}>
                        {next_visit_date.doctorName}{" "}
                        {next_visit_date.doctorSurname}
                        <br />
                        Room {next_visit_date.location}
                    </Typography>
                </React.Fragment>
            ) : (
                ""
            )}
        </React.Fragment>
    );
}

import * as React from "react";
import Typography from "@mui/material/Typography";
import Title from "./Title";
import { useCookies } from "react-cookie";
import { useEffect, useState } from "react";
import axios from "axios";
export default function Stats() {
    const [nextVisit, setNextVisit] = useState(null);
    const [cookies, setCookie] = useCookies("access_token");

    useEffect(() => {
        axios
            .get("/nextVisit/1", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setNextVisit(result.data);
            });
    }, [cookies.access_token]);
    return (
        <React.Fragment>
            <Title>Next visit</Title>
            <Typography component="p" variant="h5">
                {nextVisit === null ||
                    nextVisit === undefined ||
                    nextVisit === 0
                    ? "No upcoming visits"
                    : `${new Date(nextVisit.visitDate).getFullYear()}-${(
                        "0" +
                        (new Date(nextVisit.visitDate).getMonth() + 1)
                    ).slice(-2)}-${new Date(
                        nextVisit.visitDate
                    ).getUTCDate()}`}
            </Typography>
            {nextVisit !== null &&
                nextVisit !== undefined &&
                nextVisit !== 0 ? (
                <React.Fragment>
                    <Typography color="text.secondary" sx={{ flex: 1 }}>
                        {nextVisit.doctorName}{" "}
                        {nextVisit.doctorSurname}
                        <br />
                        Room {nextVisit.location}
                    </Typography>
                </React.Fragment>
            ) : (
                ""
            )}
        </React.Fragment>
    );
}

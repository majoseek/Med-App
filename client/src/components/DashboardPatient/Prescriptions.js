import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
} from "@mui/material";
import { useCookies } from "react-cookie";

import React, { useEffect, useState } from "react";
import Title from "./Title";
import axios from "axios";
export default function Prescriptions() {
    const [prescriptions, setPrescriptions] = useState([]);
    const [cookies, setCookie] = useCookies("access_token");
    useEffect(() => {
        axios
            .get("/prescriptions/myPrescriptions", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                console.log(result.data);
            });
    }, [prescriptions, cookies.access_token]);
    return (
        <React.Fragment>
            <Title>My prescriptions</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Name</TableCell>
                        <TableCell>Doctor</TableCell>
                        <TableCell>Visit length [min]</TableCell>
                        <TableCell>Price</TableCell>
                        <TableCell align="right">Action</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {prescriptions.map((row) => (
                        <TableRow key={`patient_${row.id}`}>
                            <TableCell>{row.date}</TableCell>
                            <TableCell>{row.patient_name}</TableCell>
                            <TableCell>{row.visit_len}</TableCell>
                            <TableCell>{`${row.price}$`}</TableCell>
                            <TableCell align="right">
                                <button
                                    className="btn btn-primary text-uppercase rounded-pill"
                                    style={{ fontSize: "14px" }}
                                >
                                    Cancel visit
                                </button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

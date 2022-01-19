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
                setPrescriptions(result.data);
            });
    }, [cookies.access_token]);
    return (
        <React.Fragment>
            <Title>My prescriptions</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Doctor</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>Amount</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {prescriptions.map((row) => (
                        row.medicationDto.map((med_row, index) => (
                            <TableRow key={`prescript_${row.id}_${index}`}>
                                <TableCell>{row.doctorName} {row.doctorSurname}</TableCell>
                                <TableCell>{med_row.medicationName}</TableCell>
                                <TableCell>{med_row.dosage}</TableCell>
                            </TableRow>
                        ))

                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

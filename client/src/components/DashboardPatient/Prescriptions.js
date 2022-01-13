import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
} from "@mui/material";
import React from "react";
import Title from "./Title";
export default function Prescriptions(props) {
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
                    {props.data.map((row) => (
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

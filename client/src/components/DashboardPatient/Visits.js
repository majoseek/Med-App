import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Title from "./Title";

const cancel_visit = (visit) => {
    if (window.confirm("Do you really want to cancel the visit?")) {
        console.log(`Cancelling visit with id ${visit}`);
    }
};
export default function Visits(props) {
    return (
        <React.Fragment>
            <Title>My visits</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
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
                                    onClick={() => cancel_visit(row.id)}
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

import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Title from "./Title";

// Generate Order Data
function createData(id, date, name, shipTo, amount) {
    return { id, date, name, shipTo, amount };
}

const rows = [
    createData(0, "16 Mar, 2019", "Elvis Presley", "Tupelo, MS", 312.44),
    createData(1, "16 Mar, 2019", "Paul McCartney", "London, UK", 866.99),
    createData(2, "16 Mar, 2019", "Tom Scholz", "Boston, MA", 100.81),
    createData(3, "16 Mar, 2019", "Michael Jackson", "Gary, IN", 654.39),
    createData(
        4,
        "15 Mar, 2019",
        "Bruce Springsteen",
        "Long Branch, NJ",
        212.79
    ),
];
export default function Orders() {
    return (
        <React.Fragment>
            <Title>My patients</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Patient</TableCell>
                        <TableCell>Visit length</TableCell>
                        <TableCell align="right">Price</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow key={row.id}>
                            <TableCell>{row.date}</TableCell>
                            <TableCell>{row.name}</TableCell>
                            <TableCell>{row.shipTo}</TableCell>
                            <TableCell align="right">{`$${row.amount}`}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Title from "./Title";
import axios from "axios";
import { useCookies } from "react-cookie";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
export default function Visits() {
    const cancel_visit = (id) => {
        if (window.confirm("Do you really want to cancel the visit?")) {
            axios.delete(`/visits/id/${id}`, {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            }).then(() => { window.location.reload() })
        }
    };
    const navigate = useNavigate();
    const [cookies, setCookie] = useCookies("access_token");
    const [myVisits, setMyVisits] = useState([]);
    useEffect(() => {
        axios
            .get("/myVisits", {
                headers: { Authorization: `Bearer ${cookies.access_token}` },
            })
            .then((result) => {
                setMyVisits(result.data);
                //TODO: refresh visits after cancel
            });
    }, [cookies.access_token]);
    return (
        <React.Fragment>
            <Title>My visits</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Doctor</TableCell>
                        <TableCell>Visit length [min]</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>Room</TableCell>
                        <TableCell align="right">Action</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {myVisits.map((row, index) => (
                        <TableRow key={`nextvisit_${row.id}_${index}`}>
                            <TableCell>{`${row.visitDate.slice(0, 10)} ${row.visitDate.slice(11, row.visitDate.length - 3)}`}</TableCell>
                            <TableCell>
                                {row.doctorName} {row.doctorSurname}
                            </TableCell>
                            <TableCell>15</TableCell>
                            <TableCell>{row.description}</TableCell>
                            <TableCell>{row.location}</TableCell>
                            <TableCell align="right">
                                <button
                                    className="btn btn-primary text-uppercase rounded-pill"
                                    style={{ fontSize: "12px" }}
                                    onClick={() => cancel_visit(row.visitId)}
                                >
                                    Cancel
                                </button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

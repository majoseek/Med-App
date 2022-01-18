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
const cancel_visit = (id) => {
      if (window.confirm("Do you really want to cancel the visit?")) {
            console.log(`Cancelling visit with id ${id}`);
            //axios.delete("/visits/id").then();
      }
};
export default function VisitsHistory() {
      const [cookies, setCookie] = useCookies("access_token");
      const [myVisits, setMyVisits] = useState([]);
      useEffect(() => {
            axios
                  .get("/visitHistory", {
                        headers: { Authorization: `Bearer ${cookies.access_token}` },
                  })
                  .then((result) => {
                        setMyVisits(result.data);
                  });
      }, [cookies.access_token]);
      return (
            <React.Fragment>
                  <Title>Visits history</Title>
                  <Table size="small">
                        <TableHead>
                              <TableRow>
                                    <TableCell>Date</TableCell>
                                    <TableCell>Doctor</TableCell>
                                    <TableCell>Description</TableCell>
                              </TableRow>
                        </TableHead>
                        <TableBody>
                              {myVisits.map((row, index) => (
                                    <TableRow key={`nextvisit_${row.id}_${index}`}>
                                          <TableCell>{`${row.visitDate.slice(0, 10)} ${row.visitDate.slice(11, row.visitDate.length - 3)}`}</TableCell>
                                          <TableCell>
                                                {row.doctorName} {row.doctorSurname}
                                          </TableCell>
                                          <TableCell>{row.description}</TableCell>
                                    </TableRow>
                              ))}
                        </TableBody>
                  </Table>
            </React.Fragment>
      );
}

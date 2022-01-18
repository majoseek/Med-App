import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";

export default function SingleVisit({ spec, doctor_id, doctor_name, doctor_surname, dates }) {
    const add_visit = () => {
        axios
            .post(
                "/visits/create",
                {
                    date: `${selectedDay}T${selectedHour}`,
                    description: `${spec}`,
                    doctor_id: doctor_id,
                    location: "104",
                },
                {
                    headers: {
                        Authorization: `Bearer ${cookies.access_token}`,
                    },
                }
            )
            .then(() => {
                navigate("/dashboard/patient");
            });
    }
    const navigate = useNavigate();
    const [cookies, setCookie] = useCookies(["access_token"])
    const [selectedDay, setSelectedDay] = useState("");
    const [selectedHour, setSelectedHour] = useState("");
    return (
        <TableRow key={`vis_${doctor_id}`}>
            <TableCell>
                {doctor_name} {doctor_surname}
            </TableCell>
            <TableCell>
                <FormControl fullWidth>
                    <Select
                        labelId={`selectday_label_${doctor_id}`}
                        id={`selectday_${doctor_id}`}
                        value={selectedDay}
                        label="Day"
                        onChange={(e) => setSelectedDay(e.target.value)}
                        style={{ padding: "2px" }}
                    >
                        {
                            dates.map((date, index) => {
                                return (
                                    index !== 0 ?
                                        (
                                            dates[index - 1].slice(0, 10) !== date.slice(0, 10) ?
                                                <MenuItem key={`hour_${date}_${doctor_id}`} value={date.slice(0, 10)}>{date.slice(0, 10)}</MenuItem>
                                                : ""
                                        ) :
                                        <MenuItem key={`hour_${date}_${doctor_id}`} value={date.slice(0, 10)}>{date.slice(0, 10)}</MenuItem>)
                            })
                        }
                    </Select>
                </FormControl>
            </TableCell>
            <TableCell><FormControl fullWidth>
                <Select
                    labelId={`selecthour_label_${doctor_id}`}
                    id={`selecthour_${doctor_id}`}
                    value={selectedHour}
                    label="Hour"
                    disabled={selectedDay === ""}
                    onChange={(e) => setSelectedHour(e.target.value)}
                    style={{ padding: "2px" }}
                >
                    {
                        dates.map((date) => {
                            return (
                                date.slice(0, 10) === selectedDay ?
                                    <MenuItem key={`hour_${date}_${doctor_id}`} value={date.slice(11, date.length)}>{date.slice(11, date.length)}</MenuItem>
                                    : "")
                        })
                    }
                </Select>
            </FormControl></TableCell>
            <TableCell>
                <Button variant="contained" disabled={selectedHour === "" || selectedDay === ""} onClick={() => add_visit()}>Add visit</Button>

            </TableCell>
        </TableRow >
    );
}
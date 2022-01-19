import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Title from "./Title";
import PropTypes from "prop-types";
import Button from "@mui/material/Button";
import { styled } from "@mui/material/styles";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import { Grid, TextField } from "@mui/material";
import axios from "axios";
import { useCookies } from "react-cookie";
const BootstrapDialog = styled(Dialog)(({ theme }) => ({
    "& .MuiDialogContent-root": {
        padding: theme.spacing(2),
    },
    "& .MuiDialogActions-root": {
        padding: theme.spacing(1),
    },
}));

const BootstrapDialogTitle = (props) => {
    const { children, onClose, ...other } = props;

    return (
        <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
            {children}
            {onClose ? (
                <IconButton
                    aria-label="close"
                    onClick={onClose}
                    sx={{
                        position: "absolute",
                        right: 8,
                        top: 8,
                        color: (theme) => theme.palette.grey[500],
                    }}
                >
                    <CloseIcon />
                </IconButton>
            ) : null}
        </DialogTitle>
    );
};

BootstrapDialogTitle.propTypes = {
    children: PropTypes.node,
    onClose: PropTypes.func.isRequired,
};
export default function Patients(props) {
    const [open, setOpen] = React.useState(false);
    const [meds, setMeds] = React.useState("");
    const [amount, setAmount] = React.useState(0);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {

        setOpen(false);
    };
    const [cookies, setCookie] = useCookies("access_token");
    const add_prescription = (visit_id, patient_id, meds, amount) => {
        axios.post("/prescriptions/create", {
            patientId: patient_id,
            amount: amount,
            medications: meds.split(' ')
        }, { headers: { Authorization: `Bearer ${cookies.access_token}` } }).then(() => {
            axios.delete(`/visits/id/${visit_id}`, { headers: { Authorization: `Bearer ${cookies.access_token}` } }).then(() => window.location.reload())
        })
    }
    return (
        <React.Fragment>
            <Title>My patients</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Patient</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell align="right">Action</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((row) => (
                        <TableRow key={`patient_${row.visitId}`}>
                            <TableCell>
                                {`${row.visitDate.slice(0, 10)} ${row.visitDate.slice(11, row.visitDate.length - 3)}`}
                            </TableCell>
                            <TableCell>
                                {row.patientName} {row.patientSurname}
                            </TableCell>
                            <TableCell>{row.description}</TableCell>
                            <TableCell align="right">
                                <button
                                    className="btn btn-primary text-uppercase rounded-pill"
                                    style={{ fontSize: "12px" }}
                                    onClick={handleClickOpen}
                                >
                                    Add prescription
                                </button>
                                <BootstrapDialog
                                    onClose={handleClose}
                                    aria-labelledby="customized-dialog-title"
                                    open={open}
                                >
                                    <BootstrapDialogTitle
                                        id="customized-dialog-title"
                                        onClose={handleClose}
                                    >
                                        Add prescription
                                    </BootstrapDialogTitle>
                                    <DialogContent dividers>
                                        <Grid
                                            container
                                            spacing={3}
                                            textAlign="center"
                                        >
                                            <Grid item xs={12}>
                                                <TextField
                                                    id={`dialog_${row.id}`}
                                                    label="Name"
                                                    value={`${row.patientName}`}
                                                    variant="standard"
                                                    fullWidth
                                                />
                                            </Grid>
                                            <Grid item xs={12}>
                                                <TextField
                                                    id={`dialog_drugs__${row.id}`}
                                                    label="Drugs"
                                                    defaultValue=""
                                                    onChange={(e) => { setMeds(e.target.value) }}
                                                    variant="standard"
                                                    fullWidth
                                                />
                                            </Grid>
                                            <Grid item xs={12}>
                                                <TextField
                                                    id={`dialog_amount__${row.id}`}
                                                    label="Amount"
                                                    defaultValue=""
                                                    onChange={(e) => { setAmount(e.target.value) }}
                                                    variant="standard"
                                                    fullWidth
                                                />
                                            </Grid>
                                        </Grid>
                                    </DialogContent>
                                    <DialogActions>
                                        <Button autoFocus onClick={() => {
                                            handleClose();
                                            add_prescription(row.visitId, row.patientId, meds, amount)
                                        }}>
                                            Add
                                        </Button>
                                    </DialogActions>
                                </BootstrapDialog>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

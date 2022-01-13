import * as React from "react";
import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import MedicalServicesIcon from "@mui/icons-material/MedicalServices";
import EventNoteIcon from "@mui/icons-material/EventNote";
import { Link } from "react-router-dom";
import LogoutIcon from "@mui/icons-material/Logout";
export default function LeftButtons() {
    return (
        <div>
            <ListItem button component={Link} to="">
                <ListItemIcon>
                    <DashboardIcon />
                </ListItemIcon>
                <ListItemText primary="Home" />
            </ListItem>

            <ListItem button component={Link} to="profile">
                <ListItemIcon>
                    <PeopleIcon />
                </ListItemIcon>
                <ListItemText primary="My profile" />
            </ListItem>
            <ListItem button component={Link} to="prescriptions">
                <ListItemIcon>
                    <MedicalServicesIcon />
                </ListItemIcon>
                <ListItemText primary="My prescriptions" />
            </ListItem>
            <ListItem button component={Link} to="visit">
                <ListItemIcon>
                    <EventNoteIcon />
                </ListItemIcon>
                <ListItemText primary="New visit" />
            </ListItem>
            <ListItem
                button
                onClick={() => {
                    console.log("Logout");
                }}
            >
                <ListItemIcon>
                    <LogoutIcon />
                </ListItemIcon>
                <ListItemText primary="Logout" />
            </ListItem>
        </div>
    );
}

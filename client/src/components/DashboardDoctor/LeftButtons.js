import * as React from "react";
import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ListSubheader from "@mui/material/ListSubheader";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import AssignmentIcon from "@mui/icons-material/Assignment";
import PostAddIcon from "@mui/icons-material/PostAdd";
import MedicalServicesIcon from "@mui/icons-material/MedicalServices";
import { Link } from "react-router-dom";
export const LeftButtons = (
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
        <ListItem button component={Link} to="drugs">
            <ListItemIcon>
                <MedicalServicesIcon />
            </ListItemIcon>
            <ListItemText primary="Drugs" />
        </ListItem>
        <ListItem button component={Link} to="prescription">
            <ListItemIcon>
                <PostAddIcon />
            </ListItemIcon>
            <ListItemText primary="Add prescription" />
        </ListItem>
    </div>
);

export const Reports = (
    <div>
        <ListSubheader inset>Reports</ListSubheader>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Current month" />
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Last quarter" />
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Last year" />
        </ListItem>
    </div>
);

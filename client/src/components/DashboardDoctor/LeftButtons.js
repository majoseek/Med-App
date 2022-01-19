import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import MedicalServicesIcon from "@mui/icons-material/MedicalServices";
import { Link } from "react-router-dom";
import { useCookies } from "react-cookie";
import LogoutIcon from "@mui/icons-material/Logout";

export default function LeftButtons() {
    const [cookies, setCookie, removeCookie] = useCookies(["access_token"]);
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
            <ListItem button component={Link} to="drugs">
                <ListItemIcon>
                    <MedicalServicesIcon />
                </ListItemIcon>
                <ListItemText primary="Medications" />
            </ListItem>
            <ListItem
                button
                onClick={() => {
                    removeCookie("access_token", { path: "/" });
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

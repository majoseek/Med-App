import * as React from "react";
import { styled, alpha } from "@mui/material/styles";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import InputBase from "@mui/material/InputBase";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";
import Button from "@mui/material/Button";
import { makeStyles } from "@mui/styles";
const Search = styled("div")(({ theme }) => ({
    position: "relative",
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha("#c5174d", 0.15),
    "&:hover": {
        backgroundColor: alpha("#c5174d", 0.25),
    },
    marginLeft: 0,
    width: "100%",
    [theme.breakpoints.up("sm")]: {
        marginLeft: theme.spacing(1),
        width: "auto",
    },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
    padding: theme.spacing(0, 2),
    height: "100%",
    position: "absolute",
    pointerEvents: "none",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
    color: "inherit",
    "& .MuiInputBase-input": {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)})`,
        transition: theme.transitions.create("width"),
        width: "100%",
        [theme.breakpoints.up("sm")]: {
            width: "12ch",
            "&:focus": {
                width: "20ch",
            },
        },
    },
}));
const useStyles = makeStyles({
    button: {
        paddingLeft: 1,
        paddingRight: 1,
        backgroundColor: "#202731",
    },
    icon: {
        backgroundColor: "#202731",
    },
});
export default function NavBar() {
    const classes = useStyles();
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" sx={{ bgcolor: "#dd3f31" }}>
                <Toolbar sx={{ justifyContent: "space-between" }}>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="open drawer"
                        sx={{ display: { xs: "block", sm: "none" }, mr: 2 }}
                        className={classes.icon}
                    >
                        <MenuIcon htmlColor="#202731" />
                    </IconButton>
                    <Typography
                        variant="h6"
                        noWrap
                        component="div"
                        sx={{
                            display: { xs: "none", sm: "block" },
                        }}
                    >
                        Logo_Nazwa
                    </Typography>
                    <Button
                        variant="contained"
                        size="large"
                        className={classes.button}
                        style={{ backgroundColor: "#202731" }}
                        sx={{
                            display: { xs: "none", sm: "block" },
                        }}
                    >
                        Home
                    </Button>
                    <Button
                        variant="contained"
                        size="large"
                        className={classes.button}
                        style={{ backgroundColor: "#202731" }}
                        sx={{
                            display: { xs: "none", sm: "block" },
                        }}
                    >
                        About
                    </Button>
                    <Button
                        variant="contained"
                        size="large"
                        className={classes.button}
                        style={{ backgroundColor: "#202731" }}
                        sx={{
                            display: { xs: "none", sm: "block" },
                        }}
                    >
                        Contact
                    </Button>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

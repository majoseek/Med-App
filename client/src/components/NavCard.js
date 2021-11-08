import { Button, CardMedia, Divider, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import Doctor_image from "../assets/doctor_card.jpg";
const useStyles = makeStyles({
    main_div: {
        backgroundColor: "white",
        maxWidth: 300,
        borderRadius: "5%",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        padding: "20px",
    },
    login_button: {
        alignSelf: "flex-start",
    },
});
export default function NavCard(props) {
    const classes = useStyles();
    return (
        <div className={classes.main_div}>
            <Typography
                variant="h6"
                color="black"
                sx={{ marginBottom: "20px" }}
            >
                Are you a {props.header_text}?
            </Typography>
            <Typography variant="subtitle1" color="black">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna.
            </Typography>
            <Button
                className={classes.login_button}
                variant="contained"
                sx={{
                    backgroundColor: "#202731",
                    margin: "15px",
                    borderRadius: "50%",
                    padding: "20px",
                    paddingLeft: "25px",
                    paddingRight: "25px",
                    color: "white",
                    "&:hover": {
                        backgroundColor: "#202731",
                    },
                }}
            >
                Log in
            </Button>
        </div>
    );
}

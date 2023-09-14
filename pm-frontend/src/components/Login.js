import React, {useState} from "react";
import PropTypes from "prop-types";

import {createTheme, getContrastRatio} from "@mui/material/styles";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import Button from "@mui/material/Button";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Typography from "@mui/material/Typography";
import {ThemeProvider} from "@emotion/react";

async function loginUser(credentials) {
    return fetch("http://localhost:8080/api/auth/authenticate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(credentials),
    }).then((response) => response.json());
}

const loginTheme = createTheme({
    palette: {
        pmLoginTheme: {
            main: "#9723ef",
            light: "#be79f2",
            dark: "#5f0b9e",
            contrastText: getContrastRatio("#9723ef", "#fff") > 4.5 ? "#fff" : "#111",
        },
    },
});

export default function Login({setToken}) {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = await loginUser({
            username,
            password,
        });
        console.log(data.token);
        setToken(data.token);
    };

    const [showPassword, setShowPassword] = React.useState(false);

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit}>
            <ThemeProvider theme={loginTheme}>
                <Typography
                    variant="h1"
                    gutterBottom
                    color="pmLoginTheme"
                    sx={{
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "center",
                        alignItems: "center",
                        color: "#9723ef",
                    }}
                >
                    PROJECT MANAGER
                </Typography>
                <Box
                    sx={{
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "center",
                        alignItems: "center",
                    }}
                >
                    <div
                        sx={{
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
                            alignItems: "center",
                        }}
                    >
                        <TextField
                            sx={{m: 1, width: "35ch"}}
                            id="outlined-basic"
                            label="Username"
                            variant="outlined"
                            type="text"
                            onChange={(e) => setUserName(e.target.value)}
                            color="pmLoginTheme"
                        />{" "}
                        <br></br>
                        <FormControl
                            sx={{m: 1, width: "35ch"}}
                            variant="outlined"
                            type="password"
                            onChange={(e) => setPassword(e.target.value)}
                            color="pmLoginTheme"
                        >
                            <InputLabel htmlFor="outlined-adornment-password">
                                Password
                            </InputLabel>
                            <OutlinedInput
                                id="outlined-adornment-password"
                                type={showPassword ? "text" : "password"}
                                endAdornment={
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label="toggle password visibility"
                                            onClick={handleClickShowPassword}
                                            onMouseDown={handleMouseDownPassword}
                                            edge="end"
                                        >
                                            {showPassword ? <VisibilityOff/> : <Visibility/>}
                                        </IconButton>
                                    </InputAdornment>
                                }
                                label="Password"
                            />
                        </FormControl>{" "}
                        <br></br>
                        <Button
                            sx={{m: 1, width: "35ch", height: "6ch"}}
                            variant="outlined"
                            type="submit"
                            color="pmLoginTheme"
                        >
                            Login
                        </Button>
                    </div>
                </Box>
            </ThemeProvider>
        </form>
    );
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired,
};

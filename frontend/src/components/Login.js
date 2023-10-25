import React, { useState } from "react";
import PropTypes from "prop-types";
import { loginUser } from "../service/UserLogin";

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
import { ThemeProvider } from "@emotion/react";
import Alert from "@mui/material/Alert";
import CloseIcon from "@mui/icons-material/Close";
import Fade from "@mui/material/Fade";
import CircularProgress from "@mui/material/CircularProgress";
import { getLoginTheme } from "../components/WebTheme";

export default function Login({ setToken, navigate }) {
  const [errorOpen, setErrorOpen] = useState(false);
  const [errorResponse, setErrorResponse] = useState(false);
  const [loading, setLoading] = useState(false);
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = await loginUser(
      {
        username,
        password,
      },
      setErrorOpen,
      setLoading,
      setErrorResponse
    );
    if (data?.token) {
      setToken(data.token);
      navigate("/home");
    }
  };

  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
    <ThemeProvider theme={getLoginTheme(true)} >
      <form onSubmit={handleSubmit}>
        <Box
          color="pmLoginTheme"
          backgroundColor="pmLoginTheme.background"
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            marginTop: "10ch",
          }}
        >
          <img src="logo.png" alt="no logo :("></img>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            height: "10ch",
          }}
          color="pmLoginTheme"
          backgroundColor="pmLoginTheme.background"
        >
          <Fade in={errorOpen || errorResponse}>
            <Alert
              sx={{
                m: 1,
                width: "32ch",
                height: "7ch",
                alignItems: "center",
              }}
              severity="error"
              action={
                <IconButton
                  aria-label="close"
                  color="inherit"
                  size="small"
                  onClick={() => {
                    setErrorOpen(false);
                    setErrorResponse(false);
                  }}
                >
                  <CloseIcon fontSize="inherit" />
                </IconButton>
              }
            >
              {errorOpen
                ? "Bad credentials, try again."
                : "Could not establish connection to server."}
            </Alert>
          </Fade>
        </Box>
        <Box
        color="pmLoginTheme"
        backgroundColor="pmLoginTheme.background"
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
              sx={{ m: 1, width: "35ch" }}
              id="outlined-basic"
              label="Username"
              variant="outlined"
              type="text"
              onChange={(e) => setUserName(e.target.value)}
              color="pmLoginTheme"
            />{" "}
            <br></br>
            <FormControl
              sx={{ m: 1, width: "35ch" }}
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
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                }
                label="Password"
              />
            </FormControl>{" "}
            <br></br>
            <Button
              sx={{ m: 1, width: "35ch", height: "6ch" }}
              variant="outlined"
              type="submit"
              color="pmLoginTheme"
            >
              Login
            </Button>
            <br></br>
            <Box
            color="pmLoginTheme"
            backgroundColor="pmLoginTheme.background"
              sx={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                marginTop: "2ch",
              }}
            >
              {loading && <CircularProgress color="pmLoginTheme" />}
            </Box>
          </div>
        </Box>
      </form>
    </ThemeProvider>
  );
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired,
};

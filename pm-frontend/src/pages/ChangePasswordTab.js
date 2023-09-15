import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import CircularProgress from "@mui/material/CircularProgress";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "../components/WebTheme";
import Alert from "@mui/material/Alert";
import CloseIcon from "@mui/icons-material/Close";
import Fade from "@mui/material/Fade";
import IconButton from "@mui/material/IconButton";
import {updatePassword} from "../service/UsersInfo";

export default function ChangePasswordTab({
  userDetails,
  token,
  setUserDetails,
}) {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [passwordsMatch, setPasswordsMatch] = useState(true);
  const [loading, setLoading] = useState(false);
  const [updateOk, setUpdateOk] = useState(false);
  const [errorUpdate, setErrorUpdate] = useState(false);
  const [allFieldsFilled, setAllFieldsFilled] = useState(false);

  const checkAllFieldsFilled = () => {
    setAllFieldsFilled(oldPassword !== "" && newPassword !== "" && confirmNewPassword !== "");
  }

  const handleOldPasswordChange = (e) => {
    setOldPassword(e.target.value);
    checkAllFieldsFilled();
  };

  const handleNewPasswordChange = (e) => {
    setNewPassword(e.target.value);
    setPasswordsMatch(e.target.value === confirmNewPassword);
    checkAllFieldsFilled();
  };

  const handleConfirmNewPasswordChange = (e) => {
    setConfirmNewPassword(e.target.value);
    setPasswordsMatch(e.target.value === newPassword);
    checkAllFieldsFilled();
  };

  const handleChangePassword = () => {
    setUpdateOk(false)
    setErrorUpdate(false)
    if (newPassword === confirmNewPassword) {
      const dataUpdatePassword = async (e) => {
        setLoading(true);
        const data = await updatePassword({ token }, oldPassword, newPassword);
        if (data !== null) {
          setUpdateOk(true);
          setUserDetails(data);
        } else if (data === null) {
          setErrorUpdate(true);
        }
        setLoading(false);
      };
      dataUpdatePassword.apply();
    }
  };

  return (
    <form>
      <ThemeProvider theme={getLoginTheme()}>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            height: "7ch",
            marginTop: "2ch",
          }}
        >
          <Fade in={updateOk || errorUpdate}>
            <Alert
              sx={{
                m: 1,
                width: "40ch",
                height: "5ch",
                alignItems: "center",
                fontSize: "120%",
              }}
              severity={updateOk === true ? "success" : "error"}
              action={
                <IconButton
                  aria-label="close"
                  color="inherit"
                  size="small"
                  onClick={() => {
                    setUpdateOk(false);
                    setErrorUpdate(false);
                  }}
                >
                  <CloseIcon fontSize="inherit" />
                </IconButton>
              }
            >
                {updateOk === true ? "Personal information are updated." : "Bad credentials, try again."}
            </Alert>
          </Fade>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <TextField
            sx={{ m: 1, width: "45ch" }}
            id="old-password"
            label="Old Password"
            variant="outlined"
            type="password"
            value={oldPassword}
            color="pmLoginTheme"
            onChange={handleOldPasswordChange}
          />
          <TextField
            sx={{ m: 1, width: "45ch" }}
            id="new-password"
            label="New Password"
            variant="outlined"
            type="password"
            value={newPassword}
            color="pmLoginTheme"
            onChange={handleNewPasswordChange}
          />
          <TextField
            sx={{ m: 1, width: "45ch" }}
            id="confirm-new-password"
            label="Confirm New Password"
            variant="outlined"
            type="password"
            color="pmLoginTheme"
            value={confirmNewPassword}
            onChange={handleConfirmNewPasswordChange}
            error={!passwordsMatch}
            helperText={!passwordsMatch && "Passwords do not match"}
          />
          <Button
            variant="contained"
            sx={{ m: 1, width: "50ch" }}
            color="pmLoginTheme"
            onClick={handleChangePassword}
            disabled={!allFieldsFilled || !passwordsMatch || loading}
          >
            Change Password
          </Button>
          {loading && (
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                marginTop: "2ch",
              }}
            >
              <CircularProgress color="primary" />
            </Box>
          )}
        </Box>
      </ThemeProvider>
    </form>
  );
}


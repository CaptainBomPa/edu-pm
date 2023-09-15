import React, { useState, useEffect } from "react";
import TextField from "@mui/material/TextField";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "../components/WebTheme";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { updateUserInfo } from "../service/UsersInfo";
import Alert from "@mui/material/Alert";
import CloseIcon from "@mui/icons-material/Close";
import Fade from "@mui/material/Fade";
import IconButton from "@mui/material/IconButton";
import CircularProgress from "@mui/material/CircularProgress";

export default function UserInformationTab({
  userDetails,
  token,
  setUserDetails,
}) {
  const [errorUpdate, setErrorUpdate] = useState(false);
  const [updateOk, setUpdateOk] = useState(false);
  const [loading, setLoading] = useState(false);

  const {
    username = "",
    firstName = "",
    lastName = "",
    team = "",
    projects = [],
  } = userDetails || {};

  const [isFormDirty, setIsFormDirty] = useState(false);

  const [currentUsername, setCurrentUsername] = useState(username);
  const [currentFirstName, setCurrentFirstName] = useState(firstName);
  const [currentLastName, setCurrentLastName] = useState(lastName);

  useEffect(() => {
    if (userDetails) {
      setCurrentUsername(userDetails.username || "");
      setCurrentFirstName(userDetails.firstName || "");
      setCurrentLastName(userDetails.lastName || "");
      setIsFormDirty(false);
    }
  }, [userDetails]);

  useEffect(() => {
    const isDirty =
      currentUsername !== userDetails?.username ||
      currentFirstName !== userDetails?.firstName ||
      currentLastName !== userDetails?.lastName;

    setIsFormDirty(isDirty);
  }, [currentUsername, currentFirstName, currentLastName, userDetails]);

  const handleUsernameChange = (e) => {
    setCurrentUsername(e.target.value);
  };

  const handleFirstNameChange = (e) => {
    setCurrentFirstName(e.target.value);
  };

  const handleLastNameChange = (e) => {
    setCurrentLastName(e.target.value);
  };

  const handleUpdate = () => {
    userDetails.username = currentUsername;
    userDetails.firstName = currentFirstName;
    userDetails.lastName = currentLastName;
    const updateUserData = async (e) => {
      setLoading(true);
      const data = await updateUserInfo(userDetails, { token });
      if (data !== null) {
        setUpdateOk(true);
        setUserDetails(data);
      } else if (data === null) {
        setErrorUpdate(true);
      }
      setLoading(false);
    };
    updateUserData.apply();
  };

  const handleRestore = () => {
    setCurrentUsername(userDetails.username || "");
    setCurrentFirstName(userDetails.firstName || "");
    setCurrentLastName(userDetails.lastName || "");
    setIsFormDirty(false);
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
            id="outlined-basic"
            label="Username"
            variant="outlined"
            type="text"
            color="pmLoginTheme"
            value={currentUsername}
            onChange={handleUsernameChange}
          />
          <TextField
            sx={{ m: 1, width: "45ch" }}
            id="outlined-basic"
            label="First Name"
            variant="outlined"
            type="text"
            color="pmLoginTheme"
            value={currentFirstName}
            onChange={handleFirstNameChange}
          />
          <TextField
            sx={{ m: 1, width: "45ch" }}
            id="outlined-basic"
            label="Last Name"
            variant="outlined"
            type="text"
            color="pmLoginTheme"
            value={currentLastName}
            onChange={handleLastNameChange}
          />
          <TextField
            disabled
            sx={{ m: 1, width: "45ch" }}
            id="outlined-basic"
            label="Team Name"
            variant="outlined"
            type="text"
            color="pmLoginTheme"
            value={
              userDetails !== null && userDetails !== undefined
                ? userDetails.team.teamName || "Team name not available" 
                : "Not assigned to any team"
            }
          />
          <TextField
            disabled
            sx={{ m: 1, width: "45ch" }}
            id="outlined-basic"
            label="Assigned Projects"
            variant="outlined"
            type="text"
            color="pmLoginTheme"
            value={
              userDetails === null || userDetails === undefined
                ? ""
                : userDetails.projects.length === 0
                ? "Not assigned to any project"
                : userDetails.projects.map(
                    (project) => ` ${project.projectName}`
                  )
            }
          />
          <Button
            variant="contained"
            sx={{ m: 1, width: "50ch" }}
            color="pmLoginTheme"
            onClick={handleUpdate}
            disabled={!isFormDirty}
          >
            Update
          </Button>
          <Button
            variant="outlined"
            sx={{ m: 1, width: "50ch" }}
            color="pmLoginTheme"
            onClick={handleRestore}
            disabled={!isFormDirty}
          >
            Restore previous information
          </Button>
          <br></br>
                        <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', marginTop: "2ch"}}>
                            {loading && (
                                <CircularProgress
                                    color="pmLoginTheme"
                                />
                            )}
                        </Box>
        </Box>
      </ThemeProvider>
    </form>
  );
}

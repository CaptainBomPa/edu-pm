import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./WebTheme";
import { useState, useEffect } from "react";
import {
  getAllFeatures,
  getAllUsers,
  updateStory,
  addUserStory,
  getAllTeams,
  getAllIterations,
} from "../service/UserStoryEdit";
import Autocomplete from "@mui/material/Autocomplete";
import { Box, Typography } from "@mui/material";
import Avatar from "@mui/material/Avatar";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import QuestionMarkIcon from "@mui/icons-material/QuestionMark";
import Tooltip, { tooltipClasses } from "@mui/material/Tooltip";
import { styled } from "@mui/material/styles";
import BlockIcon from "@mui/icons-material/Block";
import Checkbox from "@mui/material/Checkbox";

const HtmlTooltip = styled(({ className, ...props }) => (
  <Tooltip {...props} classes={{ popper: className }} />
))(({ theme }) => ({
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: "#f5f5f9",
    color: "rgba(0, 0, 0, 0.87)",
    maxWidth: 220,
    fontSize: theme.typography.pxToRem(12),
    border: "1px solid #dadde9",
  },
}));

export default function UserStoryEditDialog(props) {
  const { setOpen, edit, userStory, token, handleChangeUpdateRow } = props;

  const [userStoryName, setUserStoryName] = useState(
    userStory ? userStory.userStoryName : ""
  );
  const [storyPoints, setStoryPoints] = useState(
    userStory ? userStory.storyPoints : ""
  );
  const [selectedFeature, setSelectedFeature] = useState(
    userStory ? userStory.feature : null
  );
  const [selectedTeam, setSelectedTeam] = useState(
    userStory ? userStory.team : null
  );
  const [selectedOwner, setSelectedOwner] = useState(
    userStory ? userStory.assignedUser : null
  );
  const [storyState, setStoryState] = useState(
    userStory ? userStory.state : "NEW"
  );
  const [storyDescription, setStoryDescription] = useState(
    userStory ? userStory.description : null
  );
  const [storyBlocked, setStoryBlocked] = useState(
    userStory ? userStory.blocked : null
  );
  const [storyBlockReason, setStoryBlockReason] = useState(
    userStory ? userStory.blockReason : null
  );
  const [storyIteration, setStoryIteration] = useState(
    userStory ? userStory.iteration : null
  );

  const [features, setFeatures] = useState([]);
  const [owners, setOwners] = useState([]);
  const [teams, setTeams] = useState([]);
  const [iterations, setIterations] = useState([]);
  const [visibleOwners, setVisibleOwners] = useState([]);
  
  // if (selectedOwner && teams ) {
  //   setVisibleOwners(owners.filter((owner) => owner.team.id === selectedTeam.id))
  // }

  useEffect(() => {
    getAllFeatures(token)
      .then((features) => {
        setFeatures(features);
      })
      .catch((error) => {
        console.error(error);
      });
    getAllTeams(token)
      .then((teams) => {
        setTeams(teams);
    });
    getAllUsers(token)
      .then((users) => {
        setOwners(users);
        setVisibleOwners(users.filter((user) => user.team?.id === selectedTeam?.id))
      })
    getAllIterations(token)
      .then((iterations) => {
        setIterations(iterations);
      })
  }, [token]);

  const handleVisibleOwners = (team) => {
    if (selectedTeam === team) return;
    setSelectedOwner(null);
    setVisibleOwners(owners.filter((owner) => owner.team.id === team.id));
  }

  const handleUpdate = () => {
    const updatedStory = {
      id: userStory.id,
      userStoryName,
      storyPoints,
      feature: selectedFeature,
      assignedUser: selectedOwner,
      state: storyState,
      description: storyDescription,
      blocked: storyBlocked,
      blockReason: storyBlockReason,
      team: selectedTeam,
      iteration: storyIteration,
    };

    updateStory(updatedStory, token)
      .then((newStory) => {
        handleChangeUpdateRow(newStory);
      })
      .catch((error) => {
        console.error(
          "Błąd podczas aktualizacji historii użytkownika: ",
          error
        );
      });
  };

  const handleAdd = () => {
    const newStory = {
      userStoryName,
      storyPoints,
      feature: selectedFeature,
      assignedUser: selectedOwner,
      state: storyState,
      description: storyDescription,
      blocked: storyBlocked,
      blockReason: storyBlockReason,
      team: selectedTeam,
      iteration: storyIteration,
    };

    addUserStory(newStory, token)
      .then((addedStory) => {
        if (addedStory) {
          handleChangeUpdateRow(addedStory);
          handleClose();
        } else {
          console.error("Błąd podczas dodawania historii użytkownika.");
        }
      })
      .catch((error) => {
        console.error("Błąd podczas dodawania historii użytkownika: ", error);
      });
  };

  const handleClose = () => {
    setOpen(false);
  };

  const isFormValid = () => {
    if (
      !userStoryName.trim() ||
      !storyPoints ||
      !selectedFeature ||
      !selectedOwner
    ) {
      return false;
    }
    return true;
  };

  return (
    <div>
      <ThemeProvider theme={getLoginTheme()}>
        <Dialog
          open={true}
          onClose={handleClose}
          color="pmLoginTheme"
          maxWidth="md"
        >
          <Box
            noValidate
            component="form"
            sx={{
              display: "flex",
              flexDirection: "column",
              m: "auto",
              width: "fit-content",
            }}
          >
            <DialogTitle>{edit ? "Edit" : "Add new"} User Story</DialogTitle>
            <Box
              noValidate
              component="form"
              sx={{
                display: "grid",
                gridTemplateColumns: "1fr 1fr", // Podział na dwie kolumny
                gap: "16px", // Odstęp między kolumnami
              }}
            >
              <DialogContent fullWidth>
                <DialogContentText></DialogContentText>
                <TextField
                  autoFocus
                  margin="normal"
                  id="name"
                  label="User Story Name"
                  type="text"
                  fullWidth
                  color="pmLoginTheme"
                  value={userStoryName}
                  onChange={(e) => setUserStoryName(e.target.value)}
                />
                <TextField
                  margin="normal"
                  id="name"
                  label="Story Points"
                  type="number"
                  fullWidth
                  color="pmLoginTheme"
                  value={storyPoints}
                  onChange={(e) => setStoryPoints(e.target.value)}
                />
                <Autocomplete
                  margin="normal"
                  id="iteration-autocomplete"
                  options={iterations.sort((a, b) => {
                    const numberA = parseInt(a.itNumber, 10);
                    const numberB = parseInt(b.itNumber, 10);
                    return numberB - numberA;
                  })}
                  fullWidth
                  getOptionLabel={(option) => "Iteration " + option.itNumber}
                  isOptionEqualToValue={(option, value) =>
                    option.itNumber === value.itNumber
                  }
                  renderInput={(params) => (
                    <TextField {...params} label="Iteration" color="pmLoginTheme" />
                  )}
                  sx={{ marginTop: "12px" }}
                  color="pmLoginTheme"
                  value={storyIteration}
                  onChange={(e, newValue) => {
                    setStoryIteration(newValue)
                  }}
                />
                <Autocomplete
                  margin="normal"
                  id="team-feature-autocomplete"
                  options={features}
                  fullWidth
                  getOptionLabel={(option) => option.featureName}
                  isOptionEqualToValue={(option, value) =>
                    option.id === value.id
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Team Feature"
                      color="pmLoginTheme"
                    />
                  )}
                  sx={{ marginTop: "12px" }}
                  color="pmLoginTheme"
                  value={selectedFeature}
                  onChange={(e, newValue) => setSelectedFeature(newValue)}
                />
                <Autocomplete
                  margin="normal"
                  id="team-autocomplete"
                  options={teams}
                  fullWidth
                  getOptionLabel={(option) => option.teamName}
                  isOptionEqualToValue={(option, value) =>
                    option.id === value.id
                  }
                  renderInput={(params) => (
                    <TextField {...params} label="Team" color="pmLoginTheme" />
                  )}
                  sx={{ marginTop: "12px" }}
                  color="pmLoginTheme"
                  value={selectedTeam}
                  onChange={(e, newValue) => {
                    handleVisibleOwners(newValue)
                    setSelectedTeam(newValue)
                  }}
                />
                <Autocomplete
                  margin="normal"
                  id="owner-autocomplete"
                  options={visibleOwners}
                  fullWidth
                  getOptionLabel={(owner) =>
                    owner.firstName + " " + owner.lastName
                  }
                  isOptionEqualToValue={(option, value) =>
                    option.id === value.id
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label="Owner"
                      color="pmLoginTheme"
                      inputProps={{
                        ...params.inputProps,
                        autoComplete: "new-password",
                      }}
                    />
                  )}
                  sx={{ marginTop: "12px" }}
                  color="pmLoginTheme"
                  value={selectedOwner}
                  onChange={(e, newValue) => setSelectedOwner(newValue)}
                  renderOption={(props, option) => (
                    <Box
                      component="li"
                      sx={{ "& > img": { mr: 2, flexShrink: 0 } }}
                      {...props}
                    >
                      {option?.avatar ? (
                        <Avatar
                          sx={{ bgcolor: "gray" }}
                          src={`data:image/png;base64,${option.avatar}`}
                        />
                      ) : (
                        <Avatar sx={{ bgcolor: "gray" }}>
                          {option.firstName.charAt(0).toUpperCase() +
                            option.lastName.charAt(0).toUpperCase()}
                        </Avatar>
                      )}
                      <span style={{ marginLeft: "12px" }}>
                        {option.firstName + " " + option.lastName}
                      </span>
                    </Box>
                  )}
                />
                <ToggleButtonGroup
                  fullWidth
                  sx={{ marginTop: "12px" }}
                  value={storyState ? storyState : "NEW"}
                  exclusive
                  onChange={(event, newValue) => {
                    setStoryState(newValue);
                  }}
                  color="pmLoginTheme"
                  // aria-label="text alignment"
                >
                  <ToggleButton value="NEW">N</ToggleButton>
                  <ToggleButton value="DEFINED">D</ToggleButton>
                  <ToggleButton value="IN_PROGRESS">IP</ToggleButton>
                  <ToggleButton value="READY">R</ToggleButton>
                  <ToggleButton value="TEST">T</ToggleButton>
                  <ToggleButton value="TEST_READY">TR</ToggleButton>
                  <ToggleButton value="ACCEPTED">A</ToggleButton>
                  <ToggleButton value="CLOSED">C</ToggleButton>
                  <div
                    style={{
                      display: "inline-block",
                      position: "relative",
                      width: "24px",
                      height: "24px",
                    }}
                  >
                    <HtmlTooltip title={tooltipExplainingStoriesState}>
                      <QuestionMarkIcon
                        style={{ fontSize: "34px", margin: "5px 0" }}
                        color="pmLoginTheme"
                      />
                    </HtmlTooltip>
                  </div>
                </ToggleButtonGroup>

                <Typography>
                  Blocked:{" "}
                  <Checkbox
                    checked={storyBlocked}
                    icon={<BlockIcon sx={{ color: "gray" }} />}
                    checkedIcon={<BlockIcon sx={{ color: "red" }} />}
                    onChange={() => {
                      setStoryBlocked(!storyBlocked);
                    }}
                  />
                </Typography>

                {storyBlocked && (
                  <TextField
                    margin="dense"
                    id="blockReason"
                    label="Block Reason"
                    type="text"
                    fullWidth
                    color="pmLoginTheme"
                    value={storyBlockReason}
                    onChange={(e) => setStoryBlockReason(e.target.value)}
                  />
                )}
              </DialogContent>
              <DialogContent
                fullWidth
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  flex: 1,
                }}
              >
                <TextField
                  label="Description"
                  color="pmLoginTheme"
                  margin="normal"
                  fullWidth
                  multiline
                  value={storyDescription}
                  onChange={(e) => setStoryDescription(e.target.value)}
                  sx={{
                    height: { sm: 1000, md: 500 },
                    "& .MuiInputBase-root": {
                      position: "relative",
                      height: "100%",
                      "& .MuiInputBase-input": {
                        position: "absolute",
                        top: 0,
                        left: 0,
                        width: "93%",
                        height: "100%",
                        padding: "18px",
                      },
                    },
                  }}
                />
              </DialogContent>
            </Box>
            <DialogActions>
              <Button
                color="pmLoginTheme"
                onClick={edit ? handleUpdate : handleAdd}
                disabled={!isFormValid()}
              >
                {edit ? "Update" : "Add"}
              </Button>
              <Button color="pmLoginTheme" onClick={handleClose}>
                Cancel
              </Button>
            </DialogActions>
          </Box>
        </Dialog>
      </ThemeProvider>
    </div>
  );
}

const tooltipExplainingStoriesState = (
  <React.Fragment>
    <b>{"N (New)"}</b>
    {
      "- story is new. The goal and scope of this story has not been yet defined."
    }
    <br></br>
    <b>{"D (Defined)"}</b>
    {"- story has been defined. It's awaiting to be picked up by developer."}
    <br></br>
    <b>{"IP (In progress)"}</b>
    {"- story has been taken by developer."}
    <br></br>
    <b>{"R (Ready)"}</b>
    {
      "- story has been implemented by developer. It's awaiting to be picked up by QA."
    }
    <br></br>
    <b>{"T (Test)"}</b>
    {"- story has been taken by QA."}
    <br></br>
    <b>{"TR (Test Ready)"}</b>
    {"- story has been tested. It's awaiting PM to be accepted."}
    <br></br>
    <b>{"A (Accepted)"}</b>
    {"- story has been accepted by PM."}
    <br></br>
    <b>{"C (Closed)"}</b>
    {"- story has been closed. There will no be need for any additional work."}
    <br></br>
  </React.Fragment>
);

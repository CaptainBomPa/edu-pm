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
} from "../service/UserStoryEdit";
import Autocomplete from "@mui/material/Autocomplete";
import { Box } from "@mui/material";

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
  const [selectedOwner, setSelectedOwner] = useState(
    userStory ? userStory.assignedUser : null
  );

  const [features, setFeatures] = useState([]);
  const [owners, setOwners] = useState([]);
  useEffect(() => {
    getAllFeatures(token)
      .then((features) => {
        setFeatures(features);
      })
      .catch((error) => {
        console.error(error);
      });
    getAllUsers(token)
      .then((users) => {
        setOwners(users);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [token]);

  const handleUpdate = () => {
    const updatedStory = {
      id: userStory.id,
      userStoryName,
      storyPoints,
      feature: selectedFeature,
      assignedUser: selectedOwner,
    };

    updateStory(updatedStory, token)
      .then((newStory) => {
        handleChangeUpdateRow(newStory);
      })
      .catch((error) => {
        console.error("Błąd podczas aktualizacji historii użytkownika: ", error);
      });
  };

  const handleAdd = () => {
    const newStory = {
      userStoryName,
      storyPoints,
      feature: selectedFeature,
      assignedUser: selectedOwner,
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
    if (!userStoryName.trim() || !storyPoints || !selectedFeature || !selectedOwner) {
      return false;
    }
    return true;
  };

  return (
    <div>
      <ThemeProvider theme={getLoginTheme()}>
        <Dialog open={true} onClose={handleClose} color="pmLoginTheme">
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
                id="team-feature-autocomplete"
                options={features}
                fullWidth
                getOptionLabel={(option) => option.featureName}
                isOptionEqualToValue={(option, value) => option.id === value.id}
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
                id="owner-autocomplete"
                options={owners}
                fullWidth
                getOptionLabel={(owner) =>
                  owner.firstName + " " + owner.lastName
                }
                isOptionEqualToValue={(option, value) => option.id === value.id}
                renderInput={(params) => (
                  <TextField {...params} label="Owner" color="pmLoginTheme" />
                )}
                sx={{ marginTop: "20px" }}
                color="pmLoginTheme"
                value={selectedOwner}
                onChange={(e, newValue) => setSelectedOwner(newValue)}
              />
            </DialogContent>
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

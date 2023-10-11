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

export default function UserStoryEditDialog(props) {
  const { setOpenEdit } = props;

  const handleClose = () => {
    setOpenEdit(false);
  };

  return (
    <div>
      <ThemeProvider theme={getLoginTheme()}>
        <Dialog open={true} onClose={handleClose} color="pmLoginTheme">
          <DialogTitle>Edit User Story</DialogTitle>
          <DialogContent>
            <DialogContentText>
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="User Story Name"
              type="text"
              fullWidth
              variant="standard"
              color="pmLoginTheme"
            />
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="Story Points"
              type="number"
              fullWidth
              variant="standard"
              color="pmLoginTheme"
            />
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="TF Name"
              type="text"
              fullWidth
              variant="standard"
              color="pmLoginTheme"
            />
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="Owner"
              type="text"
              fullWidth
              variant="standard"
              color="pmLoginTheme"
            />
          </DialogContent>
          <DialogActions>
            <Button color="pmLoginTheme" onClick={handleClose}>Update</Button>
            <Button color="pmLoginTheme" onClick={handleClose}>Cancel</Button>
          </DialogActions>
        </Dialog>
      </ThemeProvider>
    </div>
  );
}

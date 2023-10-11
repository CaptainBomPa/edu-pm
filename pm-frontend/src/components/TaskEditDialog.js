import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./WebTheme";

export default function TaskEditDialog(props) {
  const { setOpenEdit, edit } = props;

  const handleClose = () => {
    setOpenEdit(false);
  };
  console.log(edit);
  return (
    <div>
      <ThemeProvider theme={getLoginTheme()}>
        <Dialog open={true} onClose={handleClose} color="pmLoginTheme">
          <DialogTitle>{edit ? "Edit" : "New"} Task</DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="name"
              label="Task Name"
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

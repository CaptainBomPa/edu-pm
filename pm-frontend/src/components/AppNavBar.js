import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import UsernameButton from "./UsernameButton";

export default function AppNavBar({onClick, setToken, firstName, lastName}) {
  let avatar = null;
  if (firstName !== null && lastName !== null) {
    avatar =
        firstName.charAt(0).toUpperCase() + lastName.charAt(0).toUpperCase();
  }

  return (
      <Box sx={{flexGrow: 1}}>
        <AppBar position="static">
          <Toolbar className="topBar">
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{mr: 2}}
                onClick={onClick}
            >
              <MenuIcon/>
            </IconButton>
            <Typography variant="h6" component="div" sx={{flexGrow: 1}}>
              PROJECT MANAGER
            </Typography>
            <Avatar sx={{bgcolor: "gray"}}>{avatar}</Avatar>
            <UsernameButton
                firstName={firstName}
                lastName={lastName}
                setToken={setToken}
            />
          </Toolbar>
        </AppBar>
      </Box>
  );
}

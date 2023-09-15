import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import UsernameButton from "./UsernameButton";
import {getUserInfo} from "../service/UsersInfo";

export default function AppNavBar({onClick, setToken, token}) {
  const [userDetails, setUserDetails] = React.useState(null);

  let team = "";
  let firstName = "";
  let lastName = "";
  let avatar = "";
  let isAdministrator = false;

  if (userDetails === null) {
    const fetchUserData = async (e) => {
      const data = await getUserInfo({token});
      setUserDetails(data);
    };
    fetchUserData.apply();
  }

  if (userDetails !== null) {
    team = userDetails.team.teamName;
    firstName = userDetails.firstName;
    lastName = userDetails.lastName;
    avatar =
        userDetails.firstName.charAt(0).toUpperCase() +
        userDetails.lastName.charAt(0).toUpperCase();
    isAdministrator = userDetails.roles.some((element) => {
      if (element === "ADMINISTRATOR") {
        return true;
      }
      return false;
    });
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
              PROJECT MANAGER - {team}
            </Typography>
            <Avatar sx={{bgcolor: "gray"}}>{avatar}</Avatar>
            <UsernameButton
                firstName={firstName}
                lastName={lastName}
                setToken={setToken}
                isAdministrator={isAdministrator}
            />
          </Toolbar>
        </AppBar>
      </Box>
  );
}

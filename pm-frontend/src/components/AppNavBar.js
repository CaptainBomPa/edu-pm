import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import UsernameButton from "./UsernameButton";
import {Link} from "react-router-dom";

export default function AppNavBar({
                                    onClick,
                                    setToken,
                                    token,
                                    userDetails,
                                    userAvatar,
                                    handleLogout,
                                  }) {
  // Zmienne przeniesione na poziom komponentu, aby zachować ich stan
  const [team, setTeam] = React.useState("");
  const [firstName, setFirstName] = React.useState("");
  const [lastName, setLastName] = React.useState("");
  const [avatar, setAvatar] = React.useState("");
  const [avatarUrl, setAvatarUrl] = React.useState();
  const [isAdministrator, setIsAdministrator] = React.useState(false);

  React.useEffect(() => {
    console.log("change");
    console.log(userAvatar);
    if (userAvatar && userAvatar.image) {
      setAvatarUrl(`data:image/png;base64,${userAvatar.image}`);
    }
  }, [userAvatar]);

  // Obsługa zmiany userDetails
  React.useEffect(() => {
    if (userDetails !== null && userDetails !== undefined) {
      if (userDetails.firstName) {
        setFirstName(userDetails.firstName);
        setAvatar(
            userDetails.firstName.charAt(0).toUpperCase() +
            userDetails.lastName.charAt(0).toUpperCase()
        );
      }
      if (userDetails.lastName) {
        setLastName(userDetails.lastName);
      }
      if (userDetails.roles && userDetails.roles.some) {
        setIsAdministrator(
            userDetails.roles.some((element) => {
              return element === "ADMINISTRATOR";
            })
        );
      }
      if (userDetails.team && userDetails.team.teamName) {
        setTeam(userDetails.team.teamName);
      }
    }
  }, [userDetails]);

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
              <Link
                  to="/home"
                  style={{textDecoration: "none", color: "inherit"}}
              >
                PROJECT MANAGER
              </Link>{" "}
              - {team}
            </Typography>
            {userAvatar && userAvatar.image && userAvatar.image.length > 2 ? (
                <Avatar
                    sx={{bgcolor: "gray"}}
                    src={`data:image/png;base64,${userAvatar.image}`}
                />
            ) : (
                <Avatar sx={{bgcolor: "gray"}}>{avatar}</Avatar>
            )}
            {/* <Avatar sx={{ bgcolor: "gray" }} src={avatarUrl} /> */}
            <UsernameButton
                firstName={firstName}
                lastName={lastName}
                setToken={setToken}
                isAdministrator={isAdministrator}
                handleLogout={handleLogout}
            />
          </Toolbar>
        </AppBar>
      </Box>
  );
}

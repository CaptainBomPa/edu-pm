import * as React from "react";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import UsernameButton from "./UsernameButton";
import { Link } from "react-router-dom";
import MuiDrawer from "@mui/material/Drawer";
import MuiAppBar from "@mui/material/AppBar";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import { getLoginTheme } from "./WebTheme";
import { styled } from "@mui/material/styles";
import { ThemeProvider } from "@emotion/react";
import AssignmentOutlinedIcon from "@mui/icons-material/AssignmentOutlined";
import AssignmentIcon from "@mui/icons-material/Assignment";
import TipsAndUpdatesOutlinedIcon from "@mui/icons-material/TipsAndUpdatesOutlined";

const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create("width", {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: "hidden",
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create("width", {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: "hidden",
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up("sm")]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled("div")(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  justifyContent: "flex-end",
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
}));

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(["width", "margin"], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(["width", "margin"], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, {
  shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
  width: drawerWidth,
  flexShrink: 0,
  whiteSpace: "nowrap",
  boxSizing: "border-box",
  ...(open && {
    ...openedMixin(theme),
    "& .MuiDrawer-paper": openedMixin(theme),
  }),
  ...(!open && {
    ...closedMixin(theme),
    "& .MuiDrawer-paper": closedMixin(theme),
  }),
}));

export default function AppNavBar({ userDetails, userAvatar, handleLogout }) {
  const [avatar, setAvatar] = React.useState("");
  const [open, setOpen] = React.useState(false);
  const theme = getLoginTheme();

  React.useEffect(() => {
    setAvatar(
      userDetails?.firstName.charAt(0).toUpperCase() +
        userDetails?.lastName.charAt(0).toUpperCase()
    );
  }, [userDetails]);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  return (
    <Box sx={{ display: "flex", flexGrow: 1 }}>
      <ThemeProvider theme={getLoginTheme()}>
        <CssBaseline />
        <AppBar position="fixed" open={open} color="pmLoginTheme">
          <Toolbar>
            <IconButton
              color="inherit"
              aria-label="open drawer"
              onClick={handleDrawerOpen}
              edge="start"
              sx={{
                marginRight: 5,
                ...(open && { display: "none" }),
              }}
            >
              <MenuIcon />
            </IconButton>
            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{ flexGrow: 1 }}
            >
              <Link
                to="/home"
                style={{ textDecoration: "none", color: "inherit" }}
              >
                PROJECT MANAGER
              </Link>{" "}
              - {userDetails?.team.teamName}
            </Typography>
            {userAvatar && userAvatar.image && userAvatar.image.length > 2 ? (
              <Avatar
                sx={{ bgcolor: "gray" }}
                src={`data:image/png;base64,${userAvatar.image}`}
              />
            ) : (
              <Avatar sx={{ bgcolor: "gray" }}>{typeof avatar === "string" ? avatar : ""}</Avatar>
            )}
            <UsernameButton
              firstName={userDetails?.firstName}
              lastName={userDetails?.lastName}
              isAdministrator={userDetails?.roles.some((element) => {
                return element === "ADMINISTRATOR";
              })}
              handleLogout={handleLogout}
            />
          </Toolbar>
        </AppBar>
        <Drawer variant="permanent" open={open}>
          <DrawerHeader>
            <IconButton onClick={handleDrawerClose}>
              {theme.direction === "rtl" ? (
                <ChevronRightIcon />
              ) : (
                <ChevronLeftIcon />
              )}
            </IconButton>
          </DrawerHeader>
          <Divider />
          <DrawerItem
            linkTo="/current-iteration"
            itemText="My Iteration"
            handleDrawerClose={handleDrawerClose}
            open={open}
            icon={<AssignmentOutlinedIcon />}
          />
          <DrawerItem
            linkTo="/iterations"
            itemText="Iterations"
            handleDrawerClose={handleDrawerClose}
            open={open}
            icon={<AssignmentIcon />}
          />
          <Divider />
          <DrawerItem
            linkTo="/features"
            itemText="Feature list"
            handleDrawerClose={handleDrawerClose}
            open={open}
            icon={<TipsAndUpdatesOutlinedIcon />}
          />
        </Drawer>
      </ThemeProvider>
    </Box>
  );
}

function DrawerItem(props) {
  const { linkTo, itemText, icon, handleDrawerClose, open } = props;

  return (
    <Link
      to={linkTo}
      style={{ textDecoration: "none", color: "inherit" }}
      onClick={handleDrawerClose}
    >
      <ListItem disablePadding sx={{ display: "block" }}>
        <ListItemButton
          sx={{
            minHeight: 48,
            justifyContent: open ? "initial" : "center",
            px: 2.5,
            color: "#000000",
          }}
        >
          <ListItemIcon
            sx={{
              minWidth: 0,
              mr: open ? 3 : "auto",
              justifyContent: "center",
              color: "#9723EF",
            }}
          >
            {icon}
          </ListItemIcon>
          <ListItemText primary={itemText} sx={{ opacity: open ? 1 : 0 }} />
        </ListItemButton>
      </ListItem>
    </Link>
  );
}

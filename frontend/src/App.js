import React, { useState } from "react";
import AppNavBar from "./components/AppNavBar";
import Login from "./components/Login";
import useToken from "./useToken";
import { Route, Routes, useNavigate } from "react-router-dom";
import Nopage from "../src/pages/NoPage";
import UserSettings from "../src/pages/UserSettings";
import { getUserAvatar, getUserInfo } from "./service/UsersInfo";
import axios from "axios";
import OtherUserStoryIterations from "./components/OtherUserStoryIterations";
import CurrentTeamIteration from "./components/CurrentTeamIteration";
import BacklogCurrentUser from "./components/BacklogCurrentUser";
import BacklogSelectTeam from "./components/BacklogSelectTeam";
import BacklogProject from "./components/BacklogProject";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./components/WebTheme";
import { Box } from "@mui/material";

function App() {
  const [navOpen, setNavOpen] = useState(false);
  const [userDetails, setUserDetails] = useState();
  const [userAvatar, setUserAvatar] = useState();
  const [useDarkMode, setUseDarkMode] = useState(true);
  const navigate = useNavigate();

  document.body.style.backgroundColor =
    getLoginTheme(useDarkMode).palette.pmLoginTheme.background;

  axios.defaults.headers.common["Content-Type"] = "application/json";
  const { token, setToken } = useToken();
  if (!token) {
    return <Login setToken={setToken} navigate={navigate} />;
  } else {
    axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  }

  if (!userDetails) {
    const fetchUserData = async (e) => {
      const data = await getUserInfo({ token });
      setUserDetails(data);
    };
    fetchUserData.apply();
  }

  if (userAvatar === null || userDetails === undefined) {
    const fetchUserAvatar = async (e) => {
      const data = await getUserAvatar({ token });
      setUserAvatar(data);
    };
    fetchUserAvatar.apply();
  }

  const handleLogout = (e) => {
    setUserDetails(null);
    setUserAvatar(null);
    setToken(null);
    sessionStorage.removeItem("token");
  };

  return (
    <ThemeProvider theme={getLoginTheme(useDarkMode)}>
      <Box>
        <AppNavBar
          onClick={() => {
            setNavOpen(!navOpen);
          }}
          setToken={setToken}
          token={token}
          userDetails={userDetails}
          userAvatar={userAvatar}
          handleLogout={handleLogout}
          useDarkMode={useDarkMode}
          setUseDarkMode={setUseDarkMode}
        />
        <Routes>
          <Route path="home" element={<Nopage />} />
          <Route
            path="current-iteration"
            element={
              <CurrentTeamIteration token={token} userDetails={userDetails} />
            }
          />
          <Route
            path="iterations"
            element={
              <OtherUserStoryIterations
                token={token}
                userDetails={userDetails}
              />
            }
          />
          <Route
            path="my-backlog"
            element={<BacklogCurrentUser userDetails={userDetails} />}
          />
          <Route
            path="backlogs"
            element={<BacklogSelectTeam userDetails={userDetails} />}
          />
          <Route
            path="project-backlog"
            element={<BacklogProject userDetails={userDetails} />}
          />
          <Route
            path="settings"
            element={
              <UserSettings
                token={token}
                userDetails={userDetails}
                setUserDetails={setUserDetails}
                userAvatar={userAvatar}
                setUserAvatar={setUserAvatar}
              />
            }
          />
          <Route path="*" element={<Nopage />} />
        </Routes>
      </Box>
    </ThemeProvider>
  );
}

export default App;

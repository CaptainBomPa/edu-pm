import React, { useState } from "react";
import AppNavBar from "./components/AppNavBar";
import Login from "./components/Login";
import useToken from "./useToken";
import { Route, Routes, useNavigate } from "react-router-dom";
import Nopage from "../src/pages/NoPage";
import UserSettings from "../src/pages/UserSettings";
import { getUserAvatar, getUserInfo } from "./service/UsersInfo";
import UserStoryTable from "./components/UserStoryTable";
import axios from "axios";

function App() {
  const [navOpen, setNavOpen] = useState(false);
  const [userDetails, setUserDetails] = useState();
  const [userAvatar, setUserAvatar] = useState();
  const navigate = useNavigate();

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
    <div>
      <AppNavBar
        onClick={() => {
          setNavOpen(!navOpen);
        }}
        setToken={setToken}
        token={token}
        userDetails={userDetails}
        userAvatar={userAvatar}
        handleLogout={handleLogout}
      />
      <Routes>
        <Route path="home" element={<Nopage />} />
        <Route
          path="current-iteration"
          element={<UserStoryTable token={token} userDetails={userDetails} />}
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
    </div>
  );
}

export default App;

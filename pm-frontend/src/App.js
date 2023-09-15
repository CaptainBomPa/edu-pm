import React, { useEffect, useState } from "react";
import AppNavBar from "./components/AppNavBar";
import NavigationList from "./components/NavigationList";
import Login from "./components/Login";
import useToken from "./useToken";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Nopage from "../src/pages/NoPage";
import UserSettings from "../src/pages/UserSettings";
import Fade from "@mui/material/Fade";
import Collapse from '@mui/material/Collapse';
import {getUserInfo} from "./service/UsersInfo";

function App() {
  const [navOpen, setNavOpen] = useState(false);
  const[userDetails, setUserDetails] = useState();

  const { token, setToken } = useToken();
  if (token === null) {
    return <Login setToken={setToken} />;
  }

  if (userDetails === null || userDetails === undefined) {
    const fetchUserData = async (e) => {
      const data = await getUserInfo({token});
      setUserDetails(data);
    };
    fetchUserData.apply();
  }

  return (
    <BrowserRouter>
      <AppNavBar
        onClick={() => {
          setNavOpen(!navOpen);
        }}
        setToken={setToken}
        token={token}
        userDetails={userDetails}
      />
      <Collapse in={navOpen}><NavigationList /></Collapse>
      <Routes>
        <Route path="home" element={<Nopage />} />
        <Route path="settings" element={<UserSettings token={token} userDetails={userDetails} setUserDetails={setUserDetails} />} />
        <Route path="*" element={<Nopage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

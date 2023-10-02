import React, {useState} from "react";
import AppNavBar from "./components/AppNavBar";
import Login from "./components/Login";
import useToken from "./useToken";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Nopage from "../src/pages/NoPage";
import UserSettings from "../src/pages/UserSettings";
import {getUserAvatar, getUserInfo} from "./service/UsersInfo";
import UserStoryTable from "./components/UserStoryTable"

function App() {
  const [navOpen, setNavOpen] = useState(false);
  const [userDetails, setUserDetails] = useState();
  const [userAvatar, setUserAvatar] = useState();

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

  if (userAvatar === null || userDetails === undefined) {
    const fetchUserAvatar = async (e) => {
      const data = await getUserAvatar({token});
      setUserAvatar(data);
    };
    fetchUserAvatar.apply();
  }

  const handleLogout = (e) => {
    setUserDetails(null)
    setUserAvatar(null)
    setToken(null)
    sessionStorage.removeItem("token");
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
            userAvatar={userAvatar}
            handleLogout={handleLogout}
        />
      <Routes>
        <Route path="home" element={<Nopage/>}/>
        <Route path="current-iteration" element={<UserStoryTable/>}/>
        <Route path="settings"
               element={<UserSettings token={token} userDetails={userDetails} setUserDetails={setUserDetails}
                                      userAvatar={userAvatar} setUserAvatar={setUserAvatar}/>}/>
        <Route path="*" element={<Nopage/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;

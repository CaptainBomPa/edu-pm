import React, {useState} from "react";
import AppNavBar from "./components/AppNavBar";
import NavigationList from "./components/NavigationList";
import Login from "./components/Login";
import useToken from "./useToken";

function App() {
    const [navOpen, setNavOpen] = useState(false);

    const {token, setToken} = useToken();
    console.log(token);
    if (!token) {
        return <Login setToken={setToken}/>;
    }
    console.log(token);

    return (
        <div>
            <AppNavBar
                onClick={() => {
                    setNavOpen(!navOpen);
                }}
                setToken={setToken}
      />
      {navOpen && <NavigationList />}
    </div>
  );
}

export default App;

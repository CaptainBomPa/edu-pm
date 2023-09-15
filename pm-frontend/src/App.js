import React, {useState} from "react";
import AppNavBar from "./components/AppNavBar";
import NavigationList from "./components/NavigationList";
import Login from "./components/Login";
import useToken from "./useToken";

function App() {
    const [navOpen, setNavOpen] = useState(false);

    const {token, setToken} = useToken();
    if (token === null) {
        return <Login setToken={setToken}/>;
    }

    return (
        <div>
            <AppNavBar
                onClick={() => {
                    setNavOpen(!navOpen);
                }}
                setToken={setToken}
                token={token}
            />
      {navOpen && <NavigationList />}
    </div>
  );
}

export default App;

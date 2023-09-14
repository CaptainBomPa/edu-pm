import React, {useState} from "react";
import AppNavBar from "./components/AppNavBar";
import NavigationList from "./components/NavigationList";
import Login from "./components/Login";
import useToken from "./useToken";

async function getUserInfo(token) {
    try {
        const response = await fetch("http://localhost:8080/api/user/currentUserInfo", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token.token}`,
            },
        });

        if (response.ok) {
            const data = await response.json(); // Przetwarzanie odpowiedzi JSON
            return data
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

function App() {
    const [navOpen, setNavOpen] = useState(false);
    const [username, setUsername] = useState(null);

    const {token, setToken} = useToken();
    if (token === null) {
        return <Login setToken={setToken}/>;
    }

    const fetchUserData = async (e) => {
        const data = await getUserInfo({token});
        console.log(data)
        setUsername(data.username);
    }
    fetchUserData.apply();
    console.log(username)

    return (
        <div>
            <AppNavBar
                onClick={() => {
                    setNavOpen(!navOpen);
                }}
                setToken={setToken}
                firstName={username}
                lastName={username}
            />
      {navOpen && <NavigationList />}
    </div>
  );
}

export default App;

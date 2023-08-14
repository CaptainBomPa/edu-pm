import { useState } from "react";
import AppNavBar from "./components/AppNavBar";
import NavigationList from "./components/NavigationList";

function App() {
  const [navOpen, setNavOpen] = useState(false);
  const [logged, setLogged] = useState(true);

  if (!logged) {
    return (
      <div>
        <h1>Not logged</h1>
      </div>
    );
  }

  return (
    <div>
      <AppNavBar
        onClick={() => {
          setNavOpen(!navOpen);
        }}
      />
      {navOpen && <NavigationList />}
    </div>
  );
}

export default App;

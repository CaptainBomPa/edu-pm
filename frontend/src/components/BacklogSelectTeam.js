import { useState, useEffect } from "react";
import {
  getBacklogForCurrentUser,
  getBacklogForTeam,
} from "../service/UserStoryBacklog";
import UserStoryTable from "./UserStoryTable";
import { getAllTeams } from "../service/UserStoryEdit";
import { Box } from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./WebTheme";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

export default function BacklogSelectTeam(props) {
  const { userDetails } = props;

  const [data, setData] = useState();

  const [selectedTeam, setSelecteTeam] = useState();
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    getAllTeams().then((teams) => {
      setTeams(teams);
    });
  }, []);

  const handleSelectionChanged = async () => {
    if (selectedTeam) {
      try {
        const fetchData = await getBacklogForTeam(selectedTeam.id);
        setData(fetchData);
      } catch (error) {
        console.error("Error while fetching data", error);
      }
    }
  };

  useEffect(() => {
    handleSelectionChanged();
  }, [selectedTeam]);

  return (
    <div>
      <Box
        sx={{
          width: "100% - 64px",
          marginLeft: "64px",
          marginTop: "84px",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <ThemeProvider theme={getLoginTheme()}>
          <FormControl sx={{ marginRight: "1%" }}>
            <InputLabel id="team-label" color="pmLoginTheme">
              Team
            </InputLabel>
            <Select
              labelId="team-label"
              id="team-select-id"
              value={selectedTeam}
              label="Team"
              onChange={(e) => {
                setSelecteTeam(e.target.value);
              }}
              sx={{ minWidth: "200px" }}
              color="pmLoginTheme"
            >
              {teams.map((team) => {
                return <MenuItem value={team}>{team.teamName}</MenuItem>;
              })}
            </Select>
          </FormControl>
        </ThemeProvider>
      </Box>
      {data && (
        <UserStoryTable
          userDetails={userDetails}
          data={data}
          setData={setData}
          team={selectedTeam}
        />
      )}
    </div>
  );
}

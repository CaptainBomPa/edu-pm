import { useState, useEffect } from "react";
import {
  getBacklogForTeam,
} from "../service/UserStoryBacklog";
import UserStoryTable from "./UserStoryTable";
import { getAllTeams } from "../service/UserStoryEdit";
import { Box } from "@mui/material";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";
import { getLoginTheme } from "./WebTheme";

function getStyles2(name, personName, theme) {
  if (!personName) {
    return {
      backgroundColor: theme.palette.pmLoginTheme.background,
      fontWeight: theme.typography.fontWeightRegular,
    };
  }
  return {
    backgroundColor:
      personName.id !== name.id
        ? theme.palette.pmLoginTheme.background
        : theme.palette.pmLoginTheme.lightMain,
    fontWeight:
      personName.id !== name.id
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

export default function BacklogSelectTeam(props) {
  const { userDetails, useDarkMode } = props;

  const [data, setData] = useState();

  const [selectedTeam, setSelecteTeam] = useState();
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    getAllTeams().then((teams) => {
      setTeams(teams);
    });
  }, []);


  useEffect(() => { 
    async function fetchData() {
      if (selectedTeam) {
        try {
          const fetchData = await getBacklogForTeam(selectedTeam.id);
          setData(fetchData);
        } catch (error) {
          console.error("Error while fetching data", error);
        }
      }
    }
    fetchData();
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
                return <MenuItem style={getStyles2(
                  team,
                  selectedTeam,
                  getLoginTheme(useDarkMode)
                )} value={team}>{team.teamName}</MenuItem>;
              })}
            </Select>
          </FormControl>
      </Box>
      {data && (
        <UserStoryTable
          userDetails={userDetails}
          data={data}
          setData={setData}
          team={selectedTeam}
          useDarkMode={useDarkMode}
        />
      )}
    </div>
  );
}

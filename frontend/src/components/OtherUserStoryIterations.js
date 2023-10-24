import { Box } from "@mui/material";
import { useState, useEffect } from "react";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";
import { getAllTeams, getAllIterations } from "../service/UserStoryEdit";
import { getLoginTheme } from "./WebTheme";
import { ThemeProvider } from "@mui/material";
import { getUserStoriesIterationTeam } from "../service/UserStoryUser";
import UserStoryTable from "./UserStoryTable";

export default function OtherUserStoryIterations(props) {
  const { token, userDetails } = props;

  const [selectedTeam, setSelecteTeam] = useState();
  const [selectedIteration, setSelectedIteratoin] = useState();

  const [teams, setTeams] = useState([]);
  const [iterations, setIterations] = useState([]);

  const [data, setData] = useState()

  useEffect(() => {
    getAllTeams().then((teams) => {
      setTeams(teams);
    });
    getAllIterations().then((iterations) => {
      setIterations(iterations);
    });
  }, []);

  const handleSelectChange = async () => {
    if (selectedTeam && selectedIteration) {
        try {
            const fetchData = await getUserStoriesIterationTeam(selectedTeam.id, selectedIteration.itNumber);
            setData(fetchData);
        } catch (error) {
            console.error("Error while fetching data", error);
        }
    }
  };

  useEffect(() => {
    handleSelectChange();
  }, [selectedTeam, selectedIteration]);

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
          <InputLabel id="team-label" color="pmLoginTheme">Team</InputLabel>
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

        <FormControl>
          <InputLabel id="iteration-label" color="pmLoginTheme">Iteration</InputLabel>
          <Select
            labelId="iteration-label"
            id="iteration-select-id"
            value={selectedIteration}
            label="Iteration"
            onChange={(e) => {
              setSelectedIteratoin(e.target.value);
            }}
            sx={{ minWidth: "200px" }}
            color="pmLoginTheme"
          >
            {iterations.map((iteration) => {
              return (
                <MenuItem value={iteration}>
                  Iteration {iteration.itNumber}
                </MenuItem>
              );
            })}
          </Select>
        </FormControl>
      </ThemeProvider>
    </Box>
    {data && <UserStoryTable token={token} userDetails={userDetails} data={data} setData={setData} team={selectedTeam} iteration={selectedIteration} />}
    </div>
  );
}

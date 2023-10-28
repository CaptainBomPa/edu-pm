import { useState, useEffect } from "react";
import { getBacklogProject } from "../service/UserStoryBacklog";
import UserStoryTable from "./UserStoryTable";

export default function BacklogProject(props) {
  const { userDetails, useDarkMode } = props;

  const [data, setData] = useState();

  useEffect(() => {
    getBacklogProject().then((data) => {
      setData(data);
    });
  }, []);

  return (
    <div>
      {data && (
        <UserStoryTable
          userDetails={userDetails}
          data={data}
          setData={setData}
          useDarkMode={useDarkMode}
        />
      )}
    </div>
  );
}

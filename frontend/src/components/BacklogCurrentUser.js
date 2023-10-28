import { useState, useEffect } from "react";
import { getBacklogForCurrentUser } from "../service/UserStoryBacklog";
import UserStoryTable from "./UserStoryTable";

export default function BacklogCurrentUser(props) {
  const { userDetails, useDarkMode } = props;

  const [data, setData] = useState();

  useEffect(() => {
    getBacklogForCurrentUser().then((data) => {
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
          team={userDetails?.team}
          useDarkMode={useDarkMode}
        />
      )}
    </div>
  );
}

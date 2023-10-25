import { useState, useEffect } from "react";
import UserStoryTable from "./UserStoryTable";
import { getUserStoriesIteration } from "../service/UserStoryUser";

export default function CurrentTeamIteration(props) {
  const { token, userDetails } = props;

  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await getUserStoriesIteration();
        setData(result);
      } catch (error) {
        console.error("Error while fetching data", error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
        {data.length > 0 && (
          <UserStoryTable
            token={token}
            userDetails={userDetails}
            data={data}
            setData={setData}
            team={userDetails?.team}
            iteration={data[0]?.iteration}
          />
        )}
    </div>
  );
}

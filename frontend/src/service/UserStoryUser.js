import axios from "axios";

export default function UserStoryUser() {}

export async function getUserStoriesIteration(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/iteration/currentForUser"
    );
    const data = response.data;
    return data;
  } catch (error) {
    console.error(error);
  }
}

export async function deleteUserStory(token, id) {
  try {
    const response = await axios.delete(
      `http://localhost:8080/api/userStory/delete/${id}`
    );
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function deleteMultipleUserStories(token, ids) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/userStory/deleteMultiple",
      ids
    );
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function deleteTask(token, id) {
  try {
    const response = await axios.delete(
      `http://localhost:8080/api/task/delete/${id}`
    );
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function addTask(token, taskData) {
  try {
    const response = await axios.put(
      "http://localhost:8080/api/task/add",
      taskData
    );
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function updateTask(token, taskData) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/task/update",
      taskData
    );
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

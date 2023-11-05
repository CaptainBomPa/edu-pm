import axios from "axios";

export default function UserStoryEdit() {}

export async function getAllFeatures(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/feature/all"
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

export async function getAllTags() {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/tags/all"
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

export async function getAllUsers(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/user/get-all-avatars"
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

export async function getAllProjects(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/project/all"
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

export async function getAllTeams(token) {
  try {
    const response = await axios.get("http://localhost:8080/api/team/get-all");
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function getAllIterations(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/iteration/all"
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

export async function updateStory(story, token) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/userStory/update",
      story
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

export async function addUserStory(userStory, token) {
  try {
    const response = await axios.put(
      "http://localhost:8080/api/userStory/add",
      userStory
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

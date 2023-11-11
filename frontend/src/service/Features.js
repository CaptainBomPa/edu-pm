import axios from "axios";

export default function Features() {}

export async function getAllWithStories() {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/feature/allWithStories"
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

export async function addNewFeature(feature) {
  try {
    const response = await axios.put(
      "http://localhost:8080/api/feature/update",
        feature
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

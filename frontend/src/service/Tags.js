import axios from "axios";

export default function Tags() {}

export async function getAllTagsWithStats() {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/tags/allWithStats"
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

export async function addNewTag(tagName) {
  try {
    const response = await axios.post("http://localhost:8080/api/tags/add", {
      tagName,
    });
    if (response.status === 200) {
      return response.data;
    } else {
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    console.error(error);
  }
}

export async function removeTag(tagId) {
    try {
      const response = await axios.delete(`http://localhost:8080/api/tags/delete/${tagId}`);
      if (response.status === 200) {
        return response.data;
      } else {
        console.error(`Error ${response.status}`);
      }
    } catch (error) {
      console.error(error);
    }
  }

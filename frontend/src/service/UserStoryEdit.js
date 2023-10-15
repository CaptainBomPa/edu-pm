export default function UserStoryEdit() {}

export async function getAllFeatures(token) {
  try {
    const response = await fetch("http://localhost:8080/api/feature/get-all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });
    if (response.ok) {
      const result = await response.json();
      return result;
    } else {
      console.error(`Błąd HTTP: ${response.status}`);
      return null;
    }
  } catch (error) {
    console.error("Błąd podczas pobierania iteracji:", error);
    return null;
  }
}

export async function getAllUsers(token) {
  try {
    const response = await fetch("http://localhost:8080/api/user/get-all-avatars", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });
    if (response.ok) {
      const result = await response.json();
      return result;
    } else {
      console.error(`Błąd HTTP: ${response.status}`);
      return null;
    }
  } catch (error) {
    console.error("Błąd podczas pobierania iteracji:", error);
    return null;
  }
}

export async function updateStory(story, token) {
  try {
    const response = await fetch("http://localhost:8080/api/userStory/update", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(story),
    });
    if (response.ok) {
      const updatedStory = await response.json();
      return updatedStory;
    } else {
      console.error(`Błąd HTTP: ${response.status}`);
      return null;
    }
  } catch (error) {
    console.error("Błąd podczas aktualizacji historii użytkownika:", error);
    return null;
  }
}

export async function addUserStory(userStory, token) {
  try {
    const response = await fetch("http://localhost:8080/api/userStory/add", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(userStory),
    });
    if (response.ok) {
      const addedStory = await response.json();
      return addedStory;
    } else {
      console.error(`Błąd HTTP: ${response.status}`);
      return null;
    }
  } catch (error) {
    console.error("Błąd podczas dodawania historii użytkownika:", error);
    return null;
  }
}

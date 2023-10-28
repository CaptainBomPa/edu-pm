import axios from "axios";

export default function UserInfo() {}

export async function getUserInfo(token) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/user/currentUserInfo`
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

export async function getUserAvatar(token) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/user/my-avatar`
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

export async function updateUserInfo(userDetails, { token }) {
  try {
    const response = await axios.post(
      `http://localhost:8080/api/user/updateUserInfo`,
      userDetails
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

export async function updatePassword({ token }, oldPassword, newPassword) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/user/changePassword",
      {
        oldPassword: oldPassword,
        newPassword: newPassword,
      }
    );
    if (response.status === 200) {
      return response.status;
    } else {
      return null;
    }
  } catch (error) {
    console.error(error);
  }
}

export async function updateFullUser(user) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/user/updateFullUserInfo",
      user
    );
    if (response.status === 200) {
      return response.data;
    } else {
      return null;
    }
  } catch (error) {
    console.error(error);
  }
}

export async function getAllBlocked(token) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/user/getAllBlocked"
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

export async function unlockUsers(users) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/user/unlockAccounts",
      users
    );
    if (response.status === 200) {
      return response.data;
    } else {
      return null;
    }
  } catch (error) {
    console.error(error);
  }
}

export async function removeUsers(users) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/user/removeAccounts",
      users
    );
    if (response.status === 200) {
      return response.data;
    } else {
      return null;
    }
  } catch (error) {
    console.error(error);
  }
}


import React from "react";

export default function UserLogin() {
}

export async function loginUser(credentials, setErrorOpen, setLoading) {
  setLoading(true);
  setErrorOpen(false);
  try {
    const response = await fetch(
        "http://localhost:8080/api/auth/authenticate",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(credentials),
        }
    );
    if (response.ok) {
      const data = await response.json();
      return data;
    } else if (response.status === 401) {
      setErrorOpen(true);
    }
  } catch (error) {
    console.error(error);
  }
  setLoading(false);
}

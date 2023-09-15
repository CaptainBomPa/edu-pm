import React from "react";

export default function UserInfo() {
}

export async function getUserInfo(token) {
    try {
        const response = await fetch(
            "http://localhost:8080/api/user/currentUserInfo",
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token.token}`,
                },
            }
        );

        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

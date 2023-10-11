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

export async function getUserAvatar(token) {
    try {
        const response = await fetch(
            "http://localhost:8080/api/user/my-avatar",
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

export async function updateUserInfo(userDetails, {token}) {
    try {
        const response = await fetch(
            "http://localhost:8080/api/user/updateUserInfo",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify(userDetails)
            }
        );
        if (response.ok) {
            return await response.json();
        } else {
            return null;
        }
    
    } catch (error) {
        console.error(error);
    }
}

export async function updatePassword({token}, oldPassword, newPassword) {
    try {
        const response = await fetch(
            "http://localhost:8080/api/user/changePassword",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({"oldPassword": oldPassword, "newPassword": newPassword})
            }
        );
        if (response.ok) {
            const data = await response.json();
            return data
        } else {
            return null;
        }
    
    } catch (error) {
        console.error(error);
    }
}

export default function UserStoryUser() {}

export async function getUserStoriesIteration(token) {
    try {
        const response = await fetch('http://localhost:8080/api/iteration/currentForUser',
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        })
        if(response.ok) {
            const result =  await response.json()
            return result
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

export async function deleteUserStory(token, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/userStory/delete/${id}`,
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        })
        if(response.ok) {
            const result =  await response.json()
            return result
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

export async function deleteMultipleUserStories(token, ids) {
    try {
        const response = await fetch('http://localhost:8080/api/userStory/deleteMultiple', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(ids),
        });

        if (response.ok) {
            const result = await response.json();
            return result;
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

export async function deleteTask(token, id) {
    try {
        const response = await fetch(`http://localhost:8080/api/task/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
        });

        if (response.ok) {
            const result = await response.json();
            return result;
        } else {
            console.error(`${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}
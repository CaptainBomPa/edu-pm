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
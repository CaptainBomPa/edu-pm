import axios from "axios";

export default function UserLogin() {
}

export async function loginUser(credentials, setErrorOpen, setLoading, setErrorResponse) {
  setLoading(true);
  setErrorOpen(false);
  setErrorResponse(false);
  
  try {
    const response = await axios.post(
      "http://localhost:8080/api/auth/authenticate", credentials
    );
    console.log(response);
    if (response.status === 200) {
      return response.data;
    } else if (response.status === 401) {
      setErrorOpen(true);
    } else {
      setErrorResponse(true)
      console.error(`Error ${response.status}`);
    }
  } catch (error) {
    setErrorResponse(true)
    console.error(error);
  }
  setLoading(false);
}

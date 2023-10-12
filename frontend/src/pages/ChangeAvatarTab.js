import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "../components/WebTheme";
import Box from "@mui/material/Box";
import CircularProgress from "@mui/material/CircularProgress";
import Alert from "@mui/material/Alert";
import CloseIcon from "@mui/icons-material/Close";
import Fade from "@mui/material/Fade";
import IconButton from "@mui/material/IconButton";

export default function ChangeAvatarTab({ userAvatar, setUserAvatar, token }) {
  const [imageUrl, setImageUrl] = useState(null);
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [isFileTooLarge, setIsFileTooLarge] = useState(false);
  const [updateOk, setUpdateOk] = useState(false);
  const [errorUpdate, setErrorUpdate] = useState(false);

  useEffect(() => {
    if (userAvatar?.image) {
      setImageUrl(`data:image/png;base64,${userAvatar.image}`);
    }
  }, [userAvatar]);

  const handleFileUpload = (event) => {
    const selectedFile = event.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      setImageUrl(reader.result);
    };

    reader.readAsDataURL(selectedFile);
    setFile(selectedFile);

    if (selectedFile.size > 1024 * 1024) {
      setIsFileTooLarge(true);
    } else {
      setIsFileTooLarge(false);
    }
  };

  const handleSaveImage = () => {
    if (!file) {
      alert("Select file first.");
      return;
    }

    if (isFileTooLarge) {
      alert("File size exceeds 1MB. Please select a smaller file.");
      return;
    }

    const formData = new FormData();
    formData.append("image", file);

    setLoading(true);

    fetch("http://localhost:8080/api/user/upload-avatar", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: formData,
    })
      .then((response) => {
        setLoading(false);
        if (response.status === 200) {
          setUpdateOk(true);
          return response.json();
        } else {
          console.error("Error during saving avatar", response.status);
          setErrorUpdate(true);
        }
      })
      .then((data) => {
        setUserAvatar(data);
      });
  };

  return (
    <ThemeProvider theme={getLoginTheme()}>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          height: "7ch",
          marginTop: "2ch",
        }}
      >
        <Fade in={updateOk || errorUpdate}>
          <Alert
            sx={{
              m: 1,
              width: "40ch",
              height: "5ch",
              alignItems: "center",
              fontSize: "120%",
            }}
            severity={
              updateOk === true
                ? "success"
                : errorUpdate === true
                ? "error"
                : "info"
            }
            action={
              <IconButton
                aria-label="close"
                color="inherit"
                size="small"
                onClick={() => {
                  setUpdateOk(false);
                  setErrorUpdate(false);
                }}
              >
                <CloseIcon fontSize="inherit" />
              </IconButton>
            }
          >
            {updateOk && "Avatar has been changed."}
            {errorUpdate && "Error during uploading avatar."}
          </Alert>
        </Fade>
      </Box>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <label htmlFor="upload-image">
          <Button variant="contained" component="span" color="pmLoginTheme">
            Select Image (.jpg .png) 1MB
          </Button>
          <input
            id="upload-image"
            hidden
            accept="image/*"
            type="file"
            onChange={handleFileUpload}
            color="primary"
          />
        </label>
        <div
          style={{
            width: "300px",
            height: "300px",
            overflow: "hidden",
            margin: "16px",
          }}
        >
          {imageUrl ? (
            <img
              src={imageUrl}
              alt="Wgrane Zdjęcie"
              width="100%"
              height="100%"
            />
          ) : (
            <div>No image selected</div>
          )}
        </div>
        <Button
          variant="contained"
          color="pmLoginTheme"
          onClick={handleSaveImage}
          style={{ marginTop: "16px" }}
          disabled={!file || isFileTooLarge}
        >
          Save as avatar
        </Button>
        {loading && <CircularProgress color="pmLoginTheme" />}
      </Box>
    </ThemeProvider>
  );
}

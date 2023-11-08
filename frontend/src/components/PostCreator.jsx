import { TextField, Button, Box } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
function PostCreator() {
  const [isDisabled, setIsDisabled] = useState(false);
  const navigate = useNavigate();
  const handleSubmit = async (event) => {
    event.preventDefault();
    const postData = new FormData(event.currentTarget);
    const response = await fetch("/api/content/post", {
      method: "POST",
      headers: {
        Authorization: localStorage.getItem("token"),
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        body: postData.get("body"),
        title: postData.get("title"),
        username: localStorage.getItem("username"),
      }),
    });
    if(response.ok){
      const responseData = await response.json();
      navigate(`/post/${responseData}`)
    }
  };
  return (
    <div>
      <Box component="form" onSubmit={handleSubmit}>
        <TextField
          sx={{ marginTop: "6px" }}
          id="post-title"
          label="Title"
          name="title"
          multiline
          rows={1}
          required
          variant="outlined"
          fullWidth
        />
        <TextField
          id="post-content"
          label="Write your post"
          name="body"
          multiline
          rows={4}
          variant="outlined"
          fullWidth
        />
        <Button
          variant="contained"
          color="primary"
          type="submit"
          disabled={isDisabled}
        >
          Post
        </Button>
      </Box>
    </div>
  );
}
export default PostCreator;

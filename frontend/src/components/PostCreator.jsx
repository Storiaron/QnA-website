import { TextField, Button, Box } from "@mui/material";
import { useState } from "react";
function PostCreator() {
  const [isDisabled, setIsDisabled] = useState(false);
  const handleSubmit = async (event) => {
    event.preventDefault();
    const postData = new FormData(event.currentTarget);
    fetch("/api/content/post", {
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

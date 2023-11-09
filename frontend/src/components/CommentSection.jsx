import { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import {
  Typography,
  List,
  ListItem,
  ListItemText,
  Divider,
  Box,
  Button,
  TextField,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
function CommentSection({ parentPostId }) {
  //TODO isInDataSavingMode is set to false for now
  const [hasMore, setHasMore] = useState(true);
  // dateOfLastLoadedContent has to be a later date than any content we want to load.
  const [dateOfLastLoadedComment, setDateOfLastLoadedComment] = useState(
    new Date("2100-01-01")
  );
  const [comments, setComments] = useState([]);
  const [isCommentTooLong, setIsCommentTooLong] = useState(false);
  const navigate = useNavigate();
  const fetchContent = async () => {
    const response = await fetch("/api/content/comment/newest", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        startingFrom: dateOfLastLoadedComment,
        isInDataSavingMode: false,
        parentPostId: parentPostId,
      }),
    });
    if (response.ok) {
      const newContent = await response.json();
      const latestComment = newContent[newContent.length - 1];
      setComments([...comments, ...newContent]);
      if (latestComment) {
        setDateOfLastLoadedComment(latestComment.timeOfWriting);
        if (latestComment.lastComment) {
          setHasMore(false);
        }
      }
    }
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    const commentData = new FormData(event.currentTarget);
    await fetch("/api/content/comment", {
      method: "POST",
      headers: {
        Authorization: localStorage.getItem("token"),
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        body: commentData.get("body"),
        username: localStorage.getItem("username"),
        parentPostId: parentPostId,
      }),
    });
  };
  useEffect(() => {
    fetchContent();
  }, []);
  return (
    <>
     {localStorage.getItem("username")? <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          margin: "auto",
          boxShadow: "4px 4px lightblue",
        }}
      >
        <TextField
          id="post-content"
          label="Write your comment"
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
          disabled={isCommentTooLong}
        >
          Submit
        </Button>
      </Box> : <Typography
            sx={{ display: "table", margin: "auto", alignItems: "center", "&:hover": {
                color: "blue",
                cursor: "pointer",
                textDecoration: "underline",
              }}}
            component="span"
            variant="body2"
            color="text.secondary"
            onClick={() => navigate("/login")}   
          >
            Login to write a comment
          </Typography>}
      <InfiniteScroll
        dataLength={comments.length}
        next={fetchContent}
        hasMore={hasMore}
        loader={<p>Loading...</p>}
        endMessage={
          <Typography
            sx={{ display: "table", margin: "auto", alignItems: "center" }}
            component="span"
            variant="body2"
            color="text.secondary"
          >
            You have seen everything...
          </Typography>
        }
      >
        <List>
          {comments.map((comment, index) => (
            <div key={comment.id}>
              <ListItem>
                <ListItemText
                  primary={comment.username}
                  secondary={comment.body}
                />
              </ListItem>
              {index < comments.length - 1 && <Divider />}
            </div>
          ))}
        </List>
      </InfiniteScroll>
    </>
  );
}
export default CommentSection;

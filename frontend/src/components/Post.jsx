import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { Box, Typography, useMediaQuery, Divider } from "@mui/material";
import timeDifferenceCalculator from "../utils/TimeDifferenceCalculator";
import CommentSection from "./CommentSection"
function Post() {
  const { id } = useParams();
  const [postData, setPostData] = useState(null);
  const isMobile = useMediaQuery((theme) => theme.breakpoints.down("sm"));
  const fetchData = async () => {
    const response = await fetch(`/api/content/post/${id}`);
    if (response.ok && response.body) {
      setPostData(await response.json());
    }
  };
  useEffect(() => {

    fetchData();
  }, []);

  return postData ? (
    <Box
      sx={{
        margin: "auto",
        boxShadow: "4px 4px lightblue",
        width: isMobile ? "100%" : "70%",
      }}
    >
      <Typography variant="h3" component="div">
        {postData.title}
      </Typography>
      <Typography variant="h6" component="div" sx={{ fontSize: '12px' }}>
        {"posted by "}
        <Link to={`/user/${postData.username}`}>{postData.username}</Link>
        {` ${timeDifferenceCalculator(postData.timeOfWriting)}`}
      </Typography>
      <Typography variant="body1" sx={{ mt: 2, borderBottom: "4px double" }}>
        {postData.body}
      </Typography>
      <Typography variant="h5" component="div">{`There are ${postData.commentCount} comments.`}</Typography>
      <CommentSection parentPostId={postData.id}/>
    </Box>
  ) : (
    <div>Loading...</div>
  );
}
export default Post;

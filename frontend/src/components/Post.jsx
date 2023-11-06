import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { Box, Typography, useMediaQuery, } from "@mui/material";
function Post() {
  const { id } = useParams();
  const [postData, setPostData] = useState(null);
  const [timeDiff, setTimeDiff] = useState("now");
  const isMobile = useMediaQuery((theme) => theme.breakpoints.down("sm"));
  console.log(postData);
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
        bgcolor: "linen",
        boxShadow: "4px 4px lightblue",
        width: isMobile ? "100%" : "70%",
      }}
    >
      <Typography variant="h3" component="div">
        {postData.title}
      </Typography>
      <Typography variant="h6" component="div" style={{ fontSize: '12px' }}>
        {"posted by "}
        <Link to={`/user/${postData.username}`}>{postData.username}</Link>
        {` ${timeDiff}`}
      </Typography>
      <Typography variant="body1" sx={{ mt: 2 }}>
        {postData.body}
      </Typography>
    </Box>
  ) : (
    <div>Loading...</div>
  );
}
export default Post;

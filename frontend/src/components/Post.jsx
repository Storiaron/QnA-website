import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { Box, Typography, Paper, useMediaQuery } from "@mui/material";
function Post(){
    const { id } = useParams();
    const [postData, setPostData] = useState(null);
    const isMobile = useMediaQuery((theme) => theme.breakpoints.down("sm"));
    console.log(postData);
    const fetchData = async() => {
        const response = await fetch(`/api/content/post/${id}`)
        if(response.ok && response.body){
            setPostData(await response.json());
        }
    }
    useEffect(()=>{
        fetchData();
    }, []);

    return postData ? (
        <Box
          component={Paper}
          sx={{
         margin: "auto",
        bgcolor: "linen",
        boxShadow: "4px 4px lightblue",
            width: isMobile ? "100%" : "70%",
          }}
        >
          <Typography variant="h6" component="div">
            {postData.title}
          </Typography>
          <Typography variant="body1" sx={{ mt: 2 }}>
            {postData.body}
          </Typography>
          {/* Add any other post information you want to display */}
        </Box>
      ): <div>Loading</div>;
}
export default Post;
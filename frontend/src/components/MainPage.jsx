import { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import PostDisplay from "./PostDisplay";
import { Typography } from "@mui/material";
function MainPage() {
  //TODO isInDataSavingMode is set to false for now
  const [hasMore, setHasMore] = useState(true);
  // dateOfLastLoadedContent has to be a later date than any content we want to load. 
  const [dateOfLastLoadedContent, setDateOfLastLoadedContent] = useState(new Date("2100-01-01"));
  const [content, setContent] = useState([]);
  const fetchContent = async () => {
    const response = await fetch("/api/content/post/newest", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        startingFrom: dateOfLastLoadedContent,
        isInDataSavingMode: false,
      }),
    });
    if (response.ok) {
      const newContent = await response.json();
      setContent([...content, ...newContent]);
      setDateOfLastLoadedContent(newContent.pop());
    }
  };
  useEffect(() => {
    fetchContent();
  }, []);
  return (
    <InfiniteScroll
      dataLength={content.length}
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
      {content.map((post) => (
        <PostDisplay
          key={post.id}
          title={post.title}
          body={post.body}
          id={post.id}
          timeOfWriting={post.timeOfWriting}
        />
      ))}
    </InfiniteScroll>
  );
}
export default MainPage;

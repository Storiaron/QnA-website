import { useEffect, useState } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import PostDisplay from "./PostDisplay";
function MainPage() {
  //TODO isInDataSavingMode is set to false for now
  const [hasMore, setHasMore] = useState(true);
  const [dateOfLastLoadedContent, setDateOfLastLoadedContent] = useState(
    new Date()
  );
  const [content, setContent] = useState([]);
  const fetchContent = async () => {
    console.log(dateOfLastLoadedContent)
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
      const oldestDate = newContent.reduce((oldest, current) => {
        const oldestDateObject = new Date(oldest.timeOfWriting);
        const currentDateObject = new Date(current.timeOfWriting);

        if (currentDateObject < oldestDateObject) {
          return current;
        }

        return oldest;
      }, newContent[0]);
      setDateOfLastLoadedContent(oldestDate.timeOfWriting);
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
      endMessage={<p>You have seen everything.</p>}
    >
      {content.map((post) => (
        <PostDisplay key={post.id} title={post.title} body={post.body} id={post.id}/>
      ))}
    </InfiniteScroll>
  );
}
export default MainPage;

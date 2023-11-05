import { useEffect, useState } from "react";
import InfiniteScroll from 'react-infinite-scroll-component';
import Post from "./Post";
function MainPage(){
    const [hasMore, setHasMore] = useState(true);
    const []
    return  <InfiniteScroll
    dataLength={items.length}
    next={fetchData}
    hasMore={hasMore}
    loader={<p>Loading...</p>}
    endMessage={<p>You have seen everything.</p>}
  >
    <ul>
      {items.map(item => (
        <li key={item.id}>{item.name}</li>
      ))}
    </ul>
  </InfiniteScroll>
}
export default MainPage;
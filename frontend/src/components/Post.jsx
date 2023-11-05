import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
function Post(){
    const { id } = useParams();
    const fetchData = async() => {

    }
    useEffect(()=>{
        fetchData();
    }, [])
}
export default Post;
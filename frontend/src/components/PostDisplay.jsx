import {
  List,
  ListItem,
  Divider,
  ListItemText,
  Typography,
  useMediaQuery,
} from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import timeDifferenceCalculator from "../utils/TimeDifferenceCalculator";
function PostDisplay(props) {
  const isMobile = useMediaQuery((theme) => theme.breakpoints.down("sm"));
  const navigate = useNavigate();
  return (
    <List
    onClick={() => navigate(`/post/${props.id}`)}
    sx={{
      display: "flex",
      justifyContent: "space-between",
      width: isMobile ? "100%" : "70%",
      margin: "auto",
      bgcolor: "linen",
      boxShadow: "4px 4px lightblue",
    }}
  >
    <ListItem alignItems="flex-start">
      <ListItemText
        primary={props.title}
        secondary={
          <Typography
            sx={{ display: "inline" }}
            component="span"
            variant="body2"
            color="text.secondary"
          >
            {props.body ? props.body.slice(0, 30) + "..." : "..."}
          </Typography>
        }
      />
    </ListItem>
    <ListItemText
      sx={{ ml: "auto" }} 
    >
      <Typography
        sx={{ fontSize: "12px" }}
        component="span"
        variant="body2"
        color="text.secondary"
        
      >
        {timeDifferenceCalculator(props.timeOfWriting)}
      </Typography>
    </ListItemText>
    <Divider variant="inset" component="li" />
  </List>
  );
}
export default PostDisplay;

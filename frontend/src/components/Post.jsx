import { List, ListItem, Divider, ListItemText, Typography } from "@mui/material";
function Post(){
    return <List sx={{ width: '70%', margin: "auto", bgcolor: 'linen', boxShadow: "4px 4px lightblue" }}>
    <ListItem alignItems="flex-start">
      <ListItemText
        primary="Brunch this weekend?"
        secondary={
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >random text</Typography>}
      />
    </ListItem>
    <Divider variant="inset" component="li" />
    </List> 
}
export default Post;
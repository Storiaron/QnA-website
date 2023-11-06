import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import AccountCircle from "@mui/icons-material/AccountCircle";
import Switch from "@mui/material/Switch";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormGroup from "@mui/material/FormGroup";
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';
import { Avatar, Button } from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import { useState } from "react";
import { Outlet, useNavigate } from "react-router-dom";

function MenuAppBar() {
  const [isLogoutSuccessOpen, setLogoutSuccessOpen] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const [menuAnchor, setMenuAnchor] = useState(null);
  let loggedInUser = localStorage.getItem("username");
  const navigate = useNavigate();
  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleLogoutSuccessOpen = () => {
    setLogoutSuccessOpen(true);
  };

  const handleLogoutSuccessClose = () => {
    setLogoutSuccessOpen(false);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleMenuClose = () => {
    setMenuAnchor(null);
  };
  const handleLog = () => {
    localStorage.getItem("username") ? handleLogOut() : handleLogin();
  };
  const handleLogOut = () => {
    handleClose();
    handleLogoutSuccessOpen();
    localStorage.clear();
    navigate("/");
  }
  const handleLogin = () => {
    navigate("/login");
  }

  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <MenuIcon />
              <Menu
               id="menu-appbar"
               anchorEl={menuAnchor}
               anchorOrigin={{
                 vertical: "top",
                 horizontal: "left",
               }}
               keepMounted
               transformOrigin={{
                 vertical: "top",
                 horizontal: "left",
               }}
               open={Boolean(menuAnchor)}
               onClose={handleMenuClose}>
                <MenuItem onClick={handleMenuClose}>Profile</MenuItem>
                <MenuItem onClick={handleMenuClose}>Log out</MenuItem>
              </Menu>
            </IconButton>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              
            </Typography>
            <div>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleMenu}
                color="inherit"
              >
                <Avatar>{localStorage.getItem("username") ? localStorage.getItem("username")[0] : "G"}</Avatar>
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorEl)}
                onClose={handleClose}
              >
                <MenuItem onClick={handleClose}>Profile</MenuItem>
                <MenuItem onClick={handleLog}>{localStorage.getItem("username") ? "Log out" : "Login"}</MenuItem>
              </Menu>
            </div>
          </Toolbar>
        </AppBar>
      </Box>
      <LogoutSuccessPopup
        open={isLogoutSuccessOpen}
        onClose={handleLogoutSuccessClose}
      />
      <Outlet />
    </div>
  );
}

function LogoutSuccessPopup({ open, onClose }) {
  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Logout Success</DialogTitle>
      <DialogContent>
        <DialogContentText>
          You have successfully logged out.
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary" autoFocus>
          Close
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default MenuAppBar;

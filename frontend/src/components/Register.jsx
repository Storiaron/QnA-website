import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { Alert } from "@mui/material";
import { useEffect, useState } from "react";
import { validatePasswordFields } from "../utils/FormValidator";
function Register() {
  const [usernameError, setUsernameError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const handleSubmit = async(event) => {
    event.preventDefault();
    let data = new FormData(event.currentTarget);
    data = {
      username: data.get("username"),
      password: data.get("password"),
      passwordRepeated: data.get("passwordRepeated"),
      email: data.get("email"),
    };
    if (validatePasswordFields(data) !== "good") {
      setPasswordError(validatePasswordFields(data));
    } else {
      delete data.passwordRepeated;
      const response = await fetch("/api/user/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });
      const wasSuccesful = await response.json();
      if(!wasSuccesful){
        setUsernameError("That username is already taken")
      }
    }
  };
  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          {usernameError && <Alert severity="error">{usernameError}</Alert>}
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            autoFocus
          />
          {passwordError && <Alert severity="error">{passwordError}</Alert>}
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="passwordRepeated"
            label="Re-enter password"
            type="password"
            id="password-repeated"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot my password
              </Link>
            </Grid>
            <Grid item xs>
              <Link href="#" variant="body2">
                {"I don't have an account"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  );
}
export default Register;

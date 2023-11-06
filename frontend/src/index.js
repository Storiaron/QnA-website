import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import Header from "./components/Header";
import MainPage from "./components/MainPage";
import Post from "./components/Post";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import PostCreator from "./components/PostCreator";
const root = ReactDOM.createRoot(document.getElementById("root"));
const theme = createTheme();
const router = createBrowserRouter([
  {
    path: "/",
    element: <Header />,
    children: [
      {
        path: "/",
        element: <MainPage />,
      },
      {
        path: "/login",
        element: <Login />,
      },
      {
        path: "/register",
        element: <Register />,
      },
      {
        path: "/post/:id",
        element: <Post />
      },
      {
        path: "/new/post",
        element: <PostCreator />
      }
    ],
  },
]);
root.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>
);
reportWebVitals();

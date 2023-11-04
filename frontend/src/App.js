import logo from './logo.svg';
import './App.css';
import Login from './components/Login';
import { ThemeProvider, createTheme } from '@mui/material';
const theme = createTheme();
function App() {
  return (
    <ThemeProvider theme={theme}>
    <div className="App">
      <Login />
    </div>
    </ThemeProvider>
  );
}

export default App;

import * as React from "react";
import { ThemeProvider, createTheme, CssBaseline } from '@mui/material';
import AuctionComponent from "./components/AuctionComponent";

const theme = createTheme();

const App: React.FC = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <div className="App">
        <AuctionComponent />
      </div>
    </ThemeProvider>
  );
}

export default App;
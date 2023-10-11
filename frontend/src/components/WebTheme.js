import { createTheme, getContrastRatio } from "@mui/material/styles";
import TableRow from '@mui/material/TableRow';

export default function WebTheme() {}

export function getLoginTheme() {
  return createTheme({
    palette: {
      pmLoginTheme: {
        main: "#9723ef",
        light: "#be79f2",
        dark: "#5f0b9e",
        contrastText:
          getContrastRatio("#9723ef", "#fff") > 4.5 ? "#fff" : "#111",
      },
    },
  });
}

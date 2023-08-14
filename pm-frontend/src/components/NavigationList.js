import * as React from "react";
import Box from "@mui/material/Box";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import InboxIcon from "@mui/icons-material/Inbox";

export default function NavigationList() {
  return (
    <Box sx={{ width: "100%", maxWidth: 360 }} className="navigationList">
      <nav aria-label="main mailbox folders">
        <List>
          <ListItem disablePadding>
            <ListItemButton>
              <ListItemIcon>
                <InboxIcon sx={{color: "#9723EF"}}/>
              </ListItemIcon>
              <ListItemText primary="Projects" />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton>
              <ListItemIcon>
                <InboxIcon sx={{color: "#9723EF"}}/>
              </ListItemIcon>
              <ListItemText primary="Features" />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton>
              <ListItemIcon>
                <InboxIcon sx={{color: "#9723EF"}}/>
              </ListItemIcon>
              <ListItemText primary="Iteration" />
            </ListItemButton>
          </ListItem>
        </List>
      </nav>
    </Box>
  );
}

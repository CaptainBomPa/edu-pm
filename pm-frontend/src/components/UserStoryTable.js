import React, { createRef, useRef, useEffect, useState } from "react";
import PropTypes from "prop-types";
import { alpha } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import TableSortLabel from "@mui/material/TableSortLabel";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import Checkbox from "@mui/material/Checkbox";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import DeleteIcon from "@mui/icons-material/Delete";
import FilterListIcon from "@mui/icons-material/FilterList";
import { visuallyHidden } from "@mui/utils";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./WebTheme";

function createData(storyNameId, storyPoints, teamFeatureName, assignedUser) {
  return {
    storyNameId,
    storyPoints,
    teamFeatureName,
    assignedUser,
  };
}

const rows = [
  createData(
    "(1) Napraw to i tamto",
    8,
    "TF-8 Utrzymanie projektu",
    "John Ohn"
  ),
  createData(
    "(2) Napraw tamto i to",
    5,
    "TF-8 Utrzymanie projektu",
    "Marie Iann"
  ),
  createData("(3) Napraw bugi", 8, "TF-8 Utrzymanie projektu", "Jan Kowalski"),
  createData(
    "(4) Dodaj coś nowego",
    5,
    "TF-3 Nowe rzeczy dla klienta nr.2",
    "John Ohn"
  ),
  createData(
    "(5) Dodaj coś superowego",
    13,
    "TF-4 Nowe rzeczy dla klienta nr.4",
    "Marie Iann"
  ),
  createData(
    "(6) Dodaj coś kolorowego",
    8,
    "TF-4 Nowe rzeczy dla klienta nr.4",
    "Jan Kowalski"
  ),
];

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === "desc"
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) {
      return order;
    }
    return a[1] - b[1];
  });
  return stabilizedThis.map((el) => el[0]);
}

const headCells = [
  {
    id: "storyNameId",
    numeric: false,
    disablePadding: false,
    label: "(ID)\u00A0Story\u00A0Name",
    field: "storyNameId",
  },
  {
    id: "storyPoints",
    numeric: true,
    disablePadding: false,
    label: "Story\u00A0Points",
    field: "storyPoints",
  },
  {
    id: "teamFeatureName",
    numeric: false,
    disablePadding: false,
    label: "TF\u00A0Name",
    field: "teamFeatureName",
  },
  {
    id: "assignedUser",
    numeric: false,
    disablePadding: false,
    label: "Owner",
    field: "assignedUser",
  },
];

const DEFAULT_MIN_WIDTH_CELL = 100;
const DEFAULT_MAX_WIDTH_CELL = 1000;

function EnhancedTableHead(props) {
  const {
    onSelectAllClick,
    order,
    orderBy,
    numSelected,
    rowCount,
    onRequestSort,
    onClickResizeColumn,
    columnRefs,
  } = props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <ThemeProvider theme={getLoginTheme()}>
      <TableHead className="tableHead">
        <TableRow>
          <TableCell padding="checkbox" className="tableCell resizable">
            <Checkbox
              color="pmLoginTheme"
              indeterminate={numSelected > 0 && numSelected < rowCount}
              checked={rowCount > 0 && numSelected === rowCount}
              onChange={onSelectAllClick}
              inputProps={{
                "aria-label": "select all desserts",
              }}
            />
          </TableCell>
          {headCells.map((headCell, colIndex) => (
            <TableCell
              className="tableCell resizable"
              key={headCell.id}
              align={"right"}
              padding={"normal"}
              sortDirection={orderBy === headCell.id ? order : false}
            >
              <TableSortLabel
                active={orderBy === headCell.id}
                direction={orderBy === headCell.id ? order : "asc"}
                onClick={createSortHandler(headCell.id)}
              >
                {headCell.label}
                {orderBy === headCell.id ? (
                  <Box component="span" sx={visuallyHidden}>
                    {order === "desc"
                      ? "sorted descending"
                      : "sorted ascending"}
                  </Box>
                ) : null}
              </TableSortLabel>
              <div
                onMouseDown={() => onClickResizeColumn(colIndex)}
                ref={columnRefs && columnRefs[colIndex]}
                className={"resizeLine"}
              />
            </TableCell>
          ))}
        </TableRow>
      </TableHead>
    </ThemeProvider>
  );
}

EnhancedTableHead.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  onSelectAllClick: PropTypes.func.isRequired,
  order: PropTypes.oneOf(["asc", "desc"]).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

function EnhancedTableToolbar(props) {
  const { numSelected } = props;

  return (
    <Toolbar
      sx={{
        pl: { sm: 2 },
        pr: { xs: 1, sm: 1 },
        ...(numSelected > 0 && {
          bgcolor: (theme) => "#f1dfff",
        }),
      }}
    >
      {numSelected > 0 ? (
        <Typography
          sx={{ flex: "1 1 100%" }}
          color="inherit"
          variant="subtitle1"
          component="div"
        >
          {numSelected} selected
        </Typography>
      ) : (
        <Typography
          sx={{ flex: "1 1 100%" }}
          variant="h6"
          id="tableTitle"
          component="div"
        >
          Iteration 12 Team 1-1
        </Typography>
      )}

      {numSelected > 0 && (
        <Tooltip title="Delete">
          <IconButton>
            <DeleteIcon />
          </IconButton>
        </Tooltip>
      )}
    </Toolbar>
  );
}

EnhancedTableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
};

export default function UserStoryTable() {
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("calories");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleRequestSort = (property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = rows.map((n) => n.storyNameId);
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleClick = (event, storyNameId) => {
    const selectedIndex = selected.indexOf(storyNameId);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, storyNameId);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const isSelected = (storyNameId) => selected.indexOf(storyNameId) !== -1;

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

  const visibleRows = React.useMemo(
    () =>
      stableSort(rows, getComparator(order, orderBy)).slice(
        page * rowsPerPage,
        page * rowsPerPage + rowsPerPage
      ),
    [order, orderBy, page, rowsPerPage]
  );

  const columnRefs = headCells.map(() => createRef());
  const isResizing = useRef(-1);

  useEffect(() => {
    document.onmousemove = handleOnMouseMove;
    document.onmouseup = handleOnMouseUp;
    loadColumnInfoLocalStorage();
    return () => {
      document.onmousemove = null;
      document.onmouseup = null;
    };
  }, []);

  function loadColumnInfoLocalStorage() {
    let columnsInfo = localStorage.getItem("columnsInfo");
    if (columnsInfo) {
      columnsInfo = JSON.parse(columnsInfo);
      Object.keys(columnsInfo).forEach((colField, index) => {
        if (
          columnRefs[index] &&
          columnRefs[index].current &&
          columnRefs[index].current.parentElement
        ) {
          columnRefs[index].current.parentElement.style.width =
            columnsInfo[colField];
        }
      });
    }
  }

  const saveColumnInfoLocalStorage = () => {
    let columnsInfo = {};
    headCells.forEach((col, index) => {
      columnsInfo[col.field] = {};
      if (
        columnRefs[index] &&
        columnRefs[index].current &&
        columnRefs[index].current.parentElement
      ) {
        columnsInfo[col.field] =
          columnRefs[index].current.parentElement.style.width;
      }
    });
    localStorage.setItem("columnsInfo", JSON.stringify(columnsInfo));
  };

  const adjustWidthColumn = (index, width) => {
    const minWidth = headCells[index]?.minWidth ?? DEFAULT_MIN_WIDTH_CELL;
    const maxWidth = columnRefs[index]?.maxWidth ?? DEFAULT_MAX_WIDTH_CELL;
    const newWidth =
      width > maxWidth ? maxWidth : width < minWidth ? minWidth : width;

    columnRefs[index].current.parentElement.style.width = newWidth + "px";
  };

  const setCursorDocument = (isResizing) => {
    document.body.style.cursor = isResizing ? "col-resize" : "auto";
  };

  const handleOnMouseMove = (e) => {
    if (
      isResizing.current >= 0 &&
      columnRefs &&
      columnRefs[isResizing.current]
    ) {
      const columnIndex = isResizing.current;
      const columnRef = columnRefs[columnIndex].current;

      if (columnRef && columnRef.parentElement) {
        const newWidth =
          e.clientX - columnRef.parentElement.getBoundingClientRect().left;
        adjustWidthColumn(columnIndex, newWidth);
      }
    }
  };

  const handleOnMouseUp = () => {
    if (isResizing.current !== -1) {
      isResizing.current = -1;
      saveColumnInfoLocalStorage();
      setCursorDocument(false);
    }
  };

  const onClickResizeColumn = (index) => {
    isResizing.current = index;
    setCursorDocument(true);
  };

  return (
    <Box sx={{ width: "100% - 64px", marginLeft: "64px", marginTop: "64px" }}>
      <ThemeProvider theme={getLoginTheme()}>
        <Paper sx={{ width: "100%", mb: 2 }}>
          <EnhancedTableToolbar numSelected={selected.length} />
          <TableContainer>
            <Table
              sx={{ minWidth: 750 }}
              aria-labelledby="tableTitle"
              size={dense ? "small" : "medium"}
              className={"table"}
            >
              <EnhancedTableHead
                numSelected={selected.length}
                order={order}
                orderBy={orderBy}
                onSelectAllClick={handleSelectAllClick}
                onRequestSort={handleRequestSort}
                rowCount={rows.length}
                onClickResizeColumn={onClickResizeColumn}
                columnRefs={columnRefs}
              />
              <TableBody>
                {visibleRows.map((row, index) => {
                  const isItemSelected = isSelected(row.storyNameId);
                  const labelId = `enhanced-table-checkbox-${index}`;

                  return (
                    <TableRow
                      hover
                      role="checkbox"
                      aria-checked={isItemSelected}
                      tabIndex={-1}
                      key={row.storyNameId}
                      selected={isItemSelected}
                      sx={{ cursor: "pointer" }}
                      className="tableRow"
                    >
                      <TableCell
                        padding="checkbox"
                        className="tableCell resizable"
                      >
                        <Checkbox
                          onClick={(event) =>
                            handleClick(event, row.storyNameId)
                          }
                          color="pmLoginTheme"
                          checked={isItemSelected}
                          inputProps={{
                            "aria-labelledby": labelId,
                          }}
                        />
                      </TableCell>
                      <TableCell
                        className="tableCell resizable"
                        component="th"
                        id={labelId}
                        scope="row"
                      >
                        {row.storyNameId}
                      </TableCell>
                      <TableCell align="right" className="tableCell resizable">
                        {row.storyPoints}
                      </TableCell>
                      <TableCell align="right" className="tableCell resizable">
                        {row.teamFeatureName}
                      </TableCell>
                      <TableCell align="right" className="tableCell resizable">
                        {row.assignedUser}
                      </TableCell>
                      <TableCell align="right" className="tableCell resizable"></TableCell>
                    </TableRow>
                  );
                })}
                {emptyRows > 0 && (
                  <TableRow
                    style={{
                      height: (dense ? 33 : 53) * emptyRows,
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25, 50, 100, 200]}
            component="div"
            count={rows.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </ThemeProvider>
    </Box>
  );
}

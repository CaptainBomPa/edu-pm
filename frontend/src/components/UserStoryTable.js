import React, { createRef, useRef, useEffect, useState } from "react";
import PropTypes from "prop-types";
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
import { visuallyHidden } from "@mui/utils";
import { ThemeProvider } from "@emotion/react";
import { getLoginTheme } from "./WebTheme";
import Collapse from "@mui/material/Collapse";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import UserStoryEditDialog from "./UserStoryEditDialog";
import TaskEditDialog from "./TaskEditDialog";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import RemoveIcon from "@mui/icons-material/Remove";
import { getUserStoriesIteration } from "../service/UserStoryUser";

function descendingComparator(a, b) {
  if (b < a) {
    return -1;
  }
  if (b > a) {
    return 1;
  }
  return 0;
}

function alphanumSort(a, b) {
  const collator = new Intl.Collator(undefined, {
    numeric: true,
    sensitivity: "base",
  });
  return collator.compare(a, b);
}

function getComparator(order, orderBy) {
  if (orderBy === "featureName") {
    return order === "desc"
      ? (a, b) => alphanumSort(b.feature[orderBy], a.feature[orderBy])
      : (a, b) => alphanumSort(a.feature[orderBy], b.feature[orderBy]);
  }
  if (orderBy === "userStoryName") {
    return order === "desc"
      ? (a, b) => alphanumSort(b[orderBy], a[orderBy])
      : (a, b) => alphanumSort(a[orderBy], b[orderBy]);
  }
  return order === "desc"
    ? (a, b) => descendingComparator(a[orderBy], b[orderBy])
    : (a, b) => -descendingComparator(a[orderBy], b[orderBy]);
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
    id: "userStoryNameId",
    numeric: true,
    disablePadding: false,
    label: "ID",
    field: "userStoryNameId",
  },
  {
    id: "userStoryName",
    numeric: false,
    disablePadding: false,
    label: "\u00A0Story\u00A0Name",
    field: "userStoryName",
  },
  {
    id: "storyPoints",
    numeric: true,
    disablePadding: false,
    label: "Story\u00A0Points",
    field: "storyPoints",
  },
  {
    id: "featureName",
    numeric: false,
    disablePadding: false,
    label: "TF\u00A0Name",
    field: "featureName",
  },
  {
    id: "assignedUser",
    numeric: false,
    disablePadding: false,
    label: "Owner",
    field: "assignedUser",
  },
];

const DEFAULT_MIN_WIDTH_CELL = 75;
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
          <TableCell className="tableCell resizable" sx={{ width: "3%" }} />
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
          <TableCell className="tableCell resizable" sx={{ width: "3%" }} />
          {headCells.map((headCell, colIndex) => (
            <TableCell
              className="tableCell resizable"
              key={headCell.id}
              align={"right"}
              padding={"normal"}
              sortDirection={orderBy === headCell.id ? order : false}
              sx={{
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
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
                ref={columnRefs[colIndex]}
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
  const { numSelected, userDetails } = props;

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
          Iteration 12 {userDetails?.team?.teamName}
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

export default function UserStoryTable(props) {
  const { token, userDetails } = props;

  const [data, setData] = useState([]);
  const [visibleRows, setVisibleRows] = useState([]);

  useEffect(() => {
    getUserStoriesIteration(token)
      .then((result) => {
        setData(result);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [token]);

  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("userStoryName");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const dense = false;

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = data?.map((n) => n.userStoryName);
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleClick = (event, userStoryName) => {
    const selectedIndex = selected.indexOf(userStoryName);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, userStoryName);
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

  const isSelected = (userStoryName) => selected.indexOf(userStoryName) !== -1;

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - data.length) : 0;

  useEffect(() => {
    const filteredAndSortedData = stableSort(
      data,
      getComparator(order, orderBy)
    ).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);
    setVisibleRows(filteredAndSortedData);
  }, [data, order, orderBy, page, rowsPerPage]);

  const [columnRefs, setColumnRefs] = useState(
    headCells.map(() => createRef())
  );

  const isResizing = useRef(-1);

  useEffect(() => {
    document.onmousemove = handleOnMouseMove;
    document.onmouseup = handleOnMouseUp;
    loadColumnInfoLocalStorage();
    return () => {
      document.onmousemove = null;
      document.onmouseup = null;
    };
  });

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

      columnRefs.current = headCells.map(() => createRef());
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
          <EnhancedTableToolbar
            numSelected={selected.length}
            userDetails={userDetails}
          />
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
                rowCount={data.length}
                onClickResizeColumn={onClickResizeColumn}
                columnRefs={columnRefs}
                setColumnRefs={setColumnRefs}
              />
              <TableBody>
                {visibleRows.map((row, index) => {
                  return (
                    <Row
                      row={row}
                      index={index}
                      isSelected={isSelected}
                      handleClick={handleClick}
                    />
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
            rowsPerPageOptions={[10, 25, 50, 100, 200]}
            component="div"
            count={data.length}
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

function Row(props) {
  const { row, index, handleClick, isSelected } = props;
  const [open, setOpen] = React.useState(false);

  const isItemSelected = isSelected(row.userStoryName);
  const labelId = `enhanced-table-checkbox-${index}`;
  const [openEdit, setOpenEdit] = React.useState(false);
  const [taskOpenEdit, setTaskOpenEdit] = React.useState(false);
  const [taskOpenAdd, setTaskOpenAdd] = React.useState(false);

  const handleEdit = (row) => {
    setOpenEdit(!openEdit);
  };

  const handleTaskEdit = (taskRow) => {
    setTaskOpenEdit(!taskOpenEdit);
  };

  const handleTaskAdd = () => {
    setTaskOpenAdd(!taskOpenAdd);
  };

  return (
    <React.Fragment>
      {taskOpenEdit && (
        <TaskEditDialog setOpenEdit={setTaskOpenEdit} edit={true} />
      )}
      {taskOpenAdd && (
        <TaskEditDialog setOpenEdit={setTaskOpenAdd} edit={false} />
      )}
      {openEdit && <UserStoryEditDialog setOpenEdit={setOpenEdit} />}
      <TableRow
        hover
        role="checkbox"
        aria-checked={isItemSelected}
        tabIndex={-1}
        key={row.id}
        selected={isItemSelected}
        sx={{ cursor: "pointer", "& > *": { borderBottom: "unset" } }}
        className="tableRow"
      >
        <TableCell
          sx={{
            width: "3%",
            padding: "0",
            textAlign: "center",
            verticalAlign: "middle",
          }}
        >
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell
          padding="checkbox"
          className="tableCell resizable"
          sx={{
            width: "3%",
            padding: "0",
            textAlign: "center",
            verticalAlign: "middle",
          }}
        >
          <Checkbox
            onClick={(event) => handleClick(event, row.userStoryName)}
            color="pmLoginTheme"
            checked={isItemSelected}
            inputProps={{
              "aria-labelledby": labelId,
            }}
          />
        </TableCell>
        <TableCell
          className="tableCell resizable"
          sx={{
            width: "3%",
            padding: "0",
            textAlign: "center",
            verticalAlign: "middle",
          }}
        >
          <IconButton>
            <ModeEditOutlineOutlinedIcon
              color="pmLoginTheme"
              onClick={() => handleEdit(row)}
            />
          </IconButton>
        </TableCell>

        <TableCell
          className="tableCell resizable"
          component="th"
          id={labelId}
          scope="row"
        >
          {row?.id}
        </TableCell>
        <TableCell
          className="tableCell resizable"
          component="th"
          id={labelId}
          scope="row"
          sx={{
            whiteSpace: "nowrap",
            overflow: "hidden",
            textOverflow: "ellipsis",
          }}
        >
          {row?.userStoryName}
        </TableCell>
        <TableCell
          align="right"
          className="tableCell resizable"
          sx={{
            whiteSpace: "nowrap",
            overflow: "hidden",
            textOverflow: "ellipsis",
          }}
        >
          {row?.storyPoints}
        </TableCell>
        <TableCell
          align="right"
          className="tableCell resizable"
          sx={{
            whiteSpace: "nowrap",
            overflow: "hidden",
            textOverflow: "ellipsis",
          }}
        >
          {row?.feature.featureName}
        </TableCell>
        <TableCell
          align="right"
          className="tableCell resizable"
          sx={{
            whiteSpace: "nowrap",
            overflow: "hidden",
            textOverflow: "ellipsis",
          }}
        >
          {row?.assignedUser.firstName} {row?.assignedUser.lastName}
        </TableCell>
        <TableCell align="right" className="tableCell resizable"></TableCell>
      </TableRow>

      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                Tasks
                <IconButton>
                  <AddCircleOutlineIcon
                    sx={{ color: "green" }}
                    onClick={() => handleTaskAdd()}
                  />
                </IconButton>
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                    <TableCell sx={{ width: "3%" }} />
                    <TableCell sx={{ width: "3%" }} />
                    <TableCell sx={{ width: "20%" }}>Task number</TableCell>
                    <TableCell>Title</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {row &&
                    row.tasks.map((task, index) => {
                      return (
                        <TableRow key={task.id}>
                          <TableCell
                            className="tableCell resizable"
                            sx={{
                              width: "3%",
                              padding: "0",
                              textAlign: "center",
                              verticalAlign: "middle",
                            }}
                          >
                            <IconButton>
                              <ModeEditOutlineOutlinedIcon
                                color="pmLoginTheme"
                                onClick={() => handleTaskEdit(row)}
                              />
                            </IconButton>
                          </TableCell>
                          <TableCell
                            className="tableCell resizable"
                            sx={{
                              width: "3%",
                              padding: "0",
                              textAlign: "center",
                              verticalAlign: "middle",
                            }}
                          >
                            <IconButton>
                              <RemoveIcon sx={{ color: "red" }} />
                            </IconButton>
                          </TableCell>

                          <TableCell
                            component="th"
                            scope="row"
                            sx={{ width: "20%" }}
                          >
                            {task.id}
                          </TableCell>
                          <TableCell>{task.taskName}</TableCell>
                        </TableRow>
                      );
                    })}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

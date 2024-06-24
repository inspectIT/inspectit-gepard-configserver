import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import AgentsPage from "./components/agents/AgentsPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <AgentsPage />,
  },
  {
    path: "/cake",
    element: <div style={{ fontSize: 150 }}>Hello from the Cake Page</div>,
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

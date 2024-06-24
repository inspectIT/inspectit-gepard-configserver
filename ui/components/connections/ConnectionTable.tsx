"use client";
import React, { useMemo } from "react";
import { useConnections } from "./useConnections";
import { ColumnDef, createColumnHelper } from "@tanstack/react-table";
import DataTable from "../ui/DataTable";
import { Connection } from "./Connection";
import ConnectionTableActions from "./ConnectionTableActions";

const columnHelper = createColumnHelper<Connection>();

const connectionColumns: any = [
  columnHelper.accessor((connection) => connection.agent.serviceName, {
    id: "serviceName",
    cell: (info) => <i>{info.getValue()}</i>,
    header: () => <span>Service Name</span>,
    footer: (props) => props.column.id,
  }),
  columnHelper.accessor((connection) => connection.agent.pid, {
    id: "pid",
    cell: (info) => <i>{info.getValue()}</i>,
    header: () => <span>Process ID</span>,
    footer: (props) => props.column.id,
  }),
  columnHelper.accessor((connection) => connection.agent.otelVersion, {
    id: "otelVersion",
    cell: (info) => <i>{info.getValue()}</i>,
    header: () => <span>Otel Version</span>,
    footer: (props) => props.column.id,
  }),
  columnHelper.accessor((connection) => connection.agent.gepardVersion, {
    id: "gepardVersion",
    cell: (info) => <i>{info.getValue()}</i>,
    header: () => <span>Gepard Version</span>,
    footer: (props) => props.column.id,
  }),
  columnHelper.accessor((connection) => connection.agent.startTime, {
    id: "startTime",
    cell: (info) => {
      const dateString = info.getValue();
      const date = new Date(dateString);

      return <i>{date.toLocaleString()}</i>;
    },
    header: () => <span>Last JVM Restart</span>,
    footer: (props) => props.column.id,
  }),
  columnHelper.accessor((connection) => connection.id, {
    id: "actions",
    cell: (info) => {
      return <ConnectionTableActions id={info.getValue()} />;
    },
    header: () => <span>Actions</span>,
    footer: (props) => props.column.id,
  }),
];

export default function connectionTable() {
  const connectionsQuery = useConnections();

  if (connectionsQuery.isSuccess) {
    return (
      <>
        <div className="w-full">
          <DataTable columns={connectionColumns} data={connectionsQuery.data} />
        </div>
      </>
    );
  }
}

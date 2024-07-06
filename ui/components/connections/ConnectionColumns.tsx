import { ColumnDef } from "@tanstack/react-table";
import SortableTableColumn from "../ui/SortableTableColumn";
import { Connection } from "./Connection";
import ConnectionTableActions from "./ConnectionTableActions";

export const connectionColumns: ColumnDef<Connection>[] = [
  {
    accessorKey: "serviceName",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="Service Name" />;
    },
    cell: ({ row }) => {
      return <div className="px-8">{row.getValue("serviceName")}</div>;
    },
  },
  {
    accessorKey: "pid",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="Process ID" />;
    },
    cell: ({ row }) => {
      return <div className="px-8">{row.getValue("pid")}</div>;
    },
    enableGlobalFilter: true,
  },
  {
    accessorKey: "javaVersion",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="Java Version" />;
    },
    cell: ({ row }) => {
      return <div className="px-8">{row.getValue("javaVersion")}</div>;
    },
  },
  {
    accessorKey: "otelVersion",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="Otel Version" />;
    },
    cell: ({ row }) => {
      return <div className="px-8">{row.getValue("otelVersion")}</div>;
    },
  },
  {
    accessorKey: "gepardVersion",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="Gepard Version" />;
    },
    cell: ({ row }) => {
      return <div className="px-8">{row.getValue("gepardVersion")}</div>;
    },
  },
  {
    accessorKey: "startTime",
    header: ({ column }) => {
      return <SortableTableColumn column={column} title="JVM Start Time" />;
    },
    cell: ({ row }) => {
      let date = new Date(0);
      date.setUTCMilliseconds(1719919263204);

      return <div className="px-8">{date.toLocaleString()}</div>;
    },
  },
  {
    id: "actions",
    header: "Actions",
    cell: ({ row }) => {
      const connection = row.original;
      return <ConnectionTableActions id={connection.id} />;
    },
  },
];

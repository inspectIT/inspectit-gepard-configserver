"use client";
import { useConnections } from "./useConnections";
import {
  AccessorFnColumnDef,
  ColumnDef,
  createColumnHelper,
} from "@tanstack/react-table";
import { ScrollArea } from "@/components/ui/scroll-area";
import DataTable from "../ui/DataTable";
import { Connection } from "./Connection";
import ConnectionTableActions from "./ConnectionTableActions";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "../ui/card";
import SortableTableColumn from "../ui/SortableTableColumn";
import { InputHTMLAttributes, useEffect, useState } from "react";

const connectionColumns: ColumnDef<Connection>[] = [
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

export default function ConnectionTable() {
  const connectionsQuery = useConnections();

  const [globalFilter, setGlobalFilter] = useState("");

  if (connectionsQuery.isSuccess) {
    return (
      <>
        <Card className="w-full border-0 h-full">
          <CardHeader>
            <CardTitle>Connections</CardTitle>
            <CardDescription>All currently connected agents.</CardDescription>
          </CardHeader>
          <CardContent>
            <div className="pb-6">
              <DebouncedInput
                value={globalFilter ?? ""}
                onChange={(value) => setGlobalFilter(String(value))}
                className="p-2 font-lg shadow border border-block"
                placeholder="Search all columns..."
              />
            </div>
            <DataTable
              columns={connectionColumns}
              data={connectionsQuery.data}
              setGlobalFilter={setGlobalFilter}
              globalFilter={globalFilter}
            />
          </CardContent>
        </Card>
      </>
    );
  }
}

// A typical debounced input react component
function DebouncedInput({
  value: initialValue,
  onChange,
  debounce = 500,
  ...props
}: {
  value: string | number;
  onChange: (value: string | number) => void;
  debounce?: number;
} & Omit<InputHTMLAttributes<HTMLInputElement>, "onChange">) {
  const [value, setValue] = useState(initialValue);

  useEffect(() => {
    setValue(initialValue);
  }, [initialValue]);

  useEffect(() => {
    const timeout = setTimeout(() => {
      onChange(value);
    }, debounce);

    return () => clearTimeout(timeout);
  }, [value]);

  return (
    <input
      {...props}
      value={value}
      onChange={(e) => setValue(e.target.value)}
    />
  );
}

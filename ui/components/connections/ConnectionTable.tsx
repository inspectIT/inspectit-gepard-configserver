"use client";
import { useConnections } from "./useConnections";
import DataTable from "../shadcn/DataTable";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "../shadcn/card";
import { useState } from "react";
import { connectionColumns } from "./ConnectionColumns";
import DebouncedInput from "../ui/DebouncedInput";

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

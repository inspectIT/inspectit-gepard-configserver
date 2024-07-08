"use client";
import { useConnections } from "../hooks/useConnections";

import { useState } from "react";
import { connectionColumns } from "./ConnectionColumns";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/shared/shadcn/card";
import DebouncedInput from "@/components/shared/atoms/DebouncedInput";
import DataTable from "@/components/shared/shadcn/DataTable";

export default function ConnectionsView() {
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

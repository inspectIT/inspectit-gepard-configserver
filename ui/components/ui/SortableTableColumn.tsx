import { ArrowUpDown } from "lucide-react";
import { Button } from "./button";
import { Column } from "@tanstack/react-table";
import { Connection } from "../connections/Connection";

interface SortableTableColumnProps {
  column: Column<Connection>;
  title: string;
}

export default function SortableTableColumn({
  column,
  title,
}: SortableTableColumnProps) {
  return (
    <Button
      variant="ghost"
      onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    >
      {title}
      <ArrowUpDown className="ml-2 h-4 w-4" />
    </Button>
  );
}

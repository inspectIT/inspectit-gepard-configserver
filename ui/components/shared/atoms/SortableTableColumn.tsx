import { ArrowUpDown } from "lucide-react";
import { Button } from "../shadcn/button";
import { Column } from "@tanstack/react-table";
import { Connection } from "@/components/connections/interfaces/Connection";

interface SortableTableColumnProps {
  column: Column<Connection>;
  title: string;
}

/*
A sortable table column that can be used in a table header.
Shows an arrow icon to indicate the sorting direction.
*/
export default function SortableTableColumn({
  column,
  title,
}: SortableTableColumnProps) {
  return (
    <Button
      variant="ghost"
      // when the button is clicked, the sorting direction is toggled
      onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    >
      {title}
      <ArrowUpDown className="ml-2 h-4 w-4" />
    </Button>
  );
}

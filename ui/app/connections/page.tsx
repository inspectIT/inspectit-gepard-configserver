import ConnectionsView from "@/components/connections/components/ConnectionsView";

/*
Renders the connections page with the connection table.
*/
export default function ConnectionsPage() {
  return (
    <div className="flex flex-col items-center justify-between h-full">
      <ConnectionsView />
    </div>
  );
}

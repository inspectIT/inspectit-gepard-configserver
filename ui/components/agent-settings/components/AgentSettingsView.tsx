import { Connection } from "@/components/connections/interfaces/Connection";
import AgentDetails from "./AgentDetails";
import AgentScopeForm from "./AgentScopeForm";

interface AgentSettingsViewProps {
  connection: Connection;
}

/*
Renders the agent settings view with the agent details and the agent scope form.
*/
export default function AgentSettingsView({
  connection,
}: AgentSettingsViewProps) {
  const startTime = connection.startTime;
  const date = new Date(startTime);

  return (
    <div className="flex flex-col gap-2">
      <AgentDetails connection={connection} />
      <AgentScopeForm connectionId={connection.id} />
    </div>
  );
}

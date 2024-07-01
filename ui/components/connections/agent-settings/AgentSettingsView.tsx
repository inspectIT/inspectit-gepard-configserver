import { Agent } from "../Agent";
import { Connection } from "../Connection";
import AgentDetails from "./AgentDetails";
import AgentScopeForm from "./AgentScopeForm";

interface AgentSettingsViewProps {
  connection: Connection;
}

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

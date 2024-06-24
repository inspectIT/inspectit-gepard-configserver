import { Agent } from "../Agent";
import AgentDetails from "./AgentDetails";
import AgentScopeForm from "./AgentScopeForm";

interface AgentSettingsViewProps {
  agent: Agent;
}

export default function AgentSettingsView({ agent }: AgentSettingsViewProps) {
  const startTime = agent.startTime;
  const date = new Date(startTime);

  return (
    <div className="flex flex-col gap-2">
      <AgentDetails agent={agent} />
      <AgentScopeForm agentId={agent.id} />
    </div>
  );
}

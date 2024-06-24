import { Agent } from "../Agent";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
export interface AgentDetailsProps {
  agent: Agent;
}
export default function AgentDetails({ agent }: AgentDetailsProps) {
  return (
    <Card className="p-2">
      <CardHeader>
        <CardTitle>{agent.serviceName}</CardTitle>
        <CardDescription>Running on {agent.pid}</CardDescription>
      </CardHeader>
      <CardContent>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">InspectIT Gepard Version</h4>
          <p className="text-sm text-muted-foreground">{agent.gepardVersion}</p>
        </div>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">OpenTelemetry Version</h4>
          <p className="text-sm text-muted-foreground">{agent.otelVersion}</p>
        </div>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">Java Version</h4>
          <p className="text-sm text-muted-foreground">{agent.javaVersion}</p>
        </div>
      </CardContent>
    </Card>
  );
}

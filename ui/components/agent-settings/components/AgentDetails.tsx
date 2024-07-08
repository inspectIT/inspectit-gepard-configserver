import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/shared/shadcn/card";
import { Connection } from "../../connections/interfaces/Connection";

export interface AgentDetailsProps {
  connection: Connection;
}

/* All the details of the agent are displayed in this component as a Card. */
export default function AgentDetails({ connection }: AgentDetailsProps) {
  return (
    <Card className="p-2">
      <CardHeader>
        <CardTitle>{connection.serviceName}</CardTitle>
        <CardDescription>Running on {connection.pid}</CardDescription>
      </CardHeader>
      <CardContent>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">InspectIT Gepard Version</h4>
          <p className="text-sm text-muted-foreground">
            {connection.gepardVersion}
          </p>
        </div>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">OpenTelemetry Version</h4>
          <p className="text-sm text-muted-foreground">
            {connection.otelVersion}
          </p>
        </div>
        <div className="space-y-1">
          <h4 className="text-sm leading-none">Java Version</h4>
          <p className="text-sm text-muted-foreground">
            {connection.javaVersion}
          </p>
        </div>
      </CardContent>
    </Card>
  );
}

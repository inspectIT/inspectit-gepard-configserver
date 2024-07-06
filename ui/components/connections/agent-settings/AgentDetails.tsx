import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/shadcn/card";
import { Connection } from "../Connection";
export interface AgentDetailsProps {
  connection: Connection;
}
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

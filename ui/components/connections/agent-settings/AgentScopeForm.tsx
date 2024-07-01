import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { UUID } from "crypto";

export default function AgentScopeForm({
  connectionId,
}: {
  connectionId: UUID;
}) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Scopes</CardTitle>
        <CardDescription>Configure the scope of this agent.</CardDescription>
      </CardHeader>
      <CardContent>
        <h1 className="text-2xl font-bold">
          Here you will have an Input Field and a List of Classes, which this
          agents configures.
        </h1>
      </CardContent>
    </Card>
  );
}

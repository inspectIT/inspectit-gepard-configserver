import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/shared/shadcn/card";
import { UUID } from "crypto";

/*
 * AgentScopeForm component is a form that allows the user to configure the scope of an agent.
 * It displays an input field and a list of classes that the agent configures.
 */
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

"use client";

import AgentSettingsView from "@/components/connections/agent-settings/AgentSettingsView";
import { Connection } from "@/components/connections/Connection";
import { useConnection } from "@/components/connections/useConnections";
import { UseQueryResult } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";

export default function connectionDetailsPage() {
  const searchParams = useSearchParams();

  const connectionId: string | null = searchParams.get("connection");
  const connectionQuery: UseQueryResult<Connection, Error> =
    useConnection(connectionId);

  if (connectionQuery?.isError) {
    return <h1>An Error occured!</h1>;
  }
  if (connectionQuery?.isLoading) {
    return <h1>Here would be a Spinner!</h1>;
  }
  if (connectionQuery?.isSuccess) {
    const agent = connectionQuery.data.agent;
    return <AgentSettingsView agent={agent} />;
  }
}

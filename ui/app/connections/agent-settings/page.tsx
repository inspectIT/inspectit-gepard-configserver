"use client";

import AgentSettingsView from "@/components/agent-settings/components/AgentSettingsView";
import { useConnection } from "@/components/connections/hooks/useConnections";
import { Connection } from "@/components/connections/interfaces/Connection";
import { UseQueryResult } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";

/*
This page is responsible for displaying the agent settings of a connection.
Based on the querystring parameter "connection" it fetches the connection.
We use queryStrings, since static exports do not support dynamic routes.
Based on the query result, we either display the agent settings, an error message or a Loading Components.
TODO: Add a Spinner Component.  
TODO: We might want to move this logic to the AgentSettingsView Component.
*/
export default function ConnectionDetailsPage() {
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
    const connection = connectionQuery.data;
    return <AgentSettingsView connection={connection} />;
  }
}

"use client";
import { useQuery, UseQueryResult } from "@tanstack/react-query";
import {
  getconnectionById,
  getconnections,
} from "../queries/ConnectionQueries";
import { Connection } from "../interfaces/Connection";

export const useConnections = () => {
  return useQuery({
    queryKey: ["connections"],
    queryFn: getconnections,
    refetchIntervalInBackground: true,
    refetchInterval: 5000,
  });
};

export const useConnection = (
  id: string | null
): UseQueryResult<Connection, Error> => {
  if (id != null) {
    return useQuery({
      queryKey: ["connection"],
      queryFn: () => getconnectionById(id),
    });
  } else {
    throw new Error("Access to Agent Settings without a Agent ID.");
  }
};

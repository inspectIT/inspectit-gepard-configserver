import { fetchData } from "@/lib/httpClient";
import { UUID } from "crypto";
import { Agent } from "./Agent";

export const getconnections = async () => {
  const data = await fetchData("/connections");
  return data;
};

export const getconnectionById = async (id: string) => {
  const data = await fetchData("/connections/" + id);
  return data;
};

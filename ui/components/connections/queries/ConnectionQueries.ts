import { fetchData } from "@/lib/httpClient";

export const getconnections = async () => {
  const data = await fetchData("/connections");
  return data;
};

export const getconnectionById = async (id: string) => {
  const data = await fetchData("/connections/" + id);
  return data;
};

import axios from "axios";

// Create an axios instance, which will be used to make requests to the backend
// We only want to create this instance once, and then reuse it for all requests
const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BACKEND_URL,
  timeout: 5000, // Timeout if necessary
  headers: {
    ContentType: "application/json",
  },
});

console.log(process.env.NEXT_PUBLIC_BACKEND_URL);

// This function will be used to make requests to the backend
// See: components/connections/quries/ConnectionQueries.ts
export const fetchData = async (url: string, options = {}) => {
  try {
    const response = await axiosInstance(url, options);
    return response.data;
  } catch (error) {
    console.error("Error retrieving data:", error);
    throw new Error("Could not get data");
  }
};

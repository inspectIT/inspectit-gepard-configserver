import axios from "axios";

const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BACKEND_URL,
  timeout: 5000, // Timeout if necessary
  headers: {
    ContentType: "application/json",
    // Add all custom headers here
  },
});

console.log(process.env.NEXT_PUBLIC_BACKEND_URL);

export const fetchData = async (url: string, options = {}) => {
  try {
    const response = await axiosInstance(url, options);
    return response.data;
  } catch (error) {
    console.error("Error retrieving data:", error);
    throw new Error("Could not get data");
  }
};

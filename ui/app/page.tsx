import { redirect } from "next/navigation";

/*
Home page redirects to the connections page.
*/
export default function Home() {
  redirect(
    process.env.NODE_ENV === "production" ? "ui/connections/" : "connections"
  );
}

import { Suspense } from "react";

/*
  This Layout is needed, because we use useParams to get the connectionId.
    This is not possible (not recommended) without a Suspense boundary.
*/
export default function AgentSettingsLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return <Suspense>{children}</Suspense>;
}

import { Suspense } from "react";

export default function connectionDetailsLayout({
  children, // will be a page or nested layout
}: {
  children: React.ReactNode;
}) {
  return (
    <Suspense>
      <section className="px-8 py-4">{children}</section>
    </Suspense>
  );
}

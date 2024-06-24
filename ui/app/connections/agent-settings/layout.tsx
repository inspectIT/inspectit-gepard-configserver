export default function connectionDetailsLayout({
  children, // will be a page or nested layout
}: {
  children: React.ReactNode;
}) {
  return <section className="px-8 py-4">{children}</section>;
}

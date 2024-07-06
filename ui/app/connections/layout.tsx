export default function connectionsLayout({
  children, // will be a page or nested layout
}: {
  children: React.ReactNode;
}) {
  return <section className="h-full">{children}</section>;
}

import type { Metadata } from "next";
import "./globals.css";

import Providers from "./providers";
import Header from "@/components/shared/header/Header";
import Sidebar from "@/components/shared/sidebar/Sidebar";

export const metadata: Metadata = {
  title: "inspectIT Gepard Configserver",
  description: "Build by Novatec",
};

/*
  The RootLayout is the main layout of the application and used as a wrapper for all pages.
  It includes the Header and Sidebar components and wraps the children (pages) in the Providers component.
*/
export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html suppressHydrationWarning={true} lang="en">
      <body>
        <Providers>
          {" "}
          <Header />
          <div className="flex h-screen border-collapse overflow-hidden">
            <Sidebar />
            <main className="flex-1 h-screen overflow-x-hidden pt-16 bg-secondary/10 pb-1">
              {children}
            </main>
          </div>
        </Providers>
      </body>
    </html>
  );
}

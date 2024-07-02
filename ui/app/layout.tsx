import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Sidebar from "@/components/sidebar/Sidebar";
import Header from "@/components/header/Header";
import Providers from "./providers";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "inspectIT Gepard Configserver",
  description: "Build by Novatec",
};

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
          <Header></Header>
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

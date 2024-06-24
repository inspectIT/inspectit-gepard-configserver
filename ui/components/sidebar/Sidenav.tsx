import { usePathname } from "next/navigation";
import { NavItem } from "./interfaces";
import { useSidebarStore } from "./useSidebarStore";
import { useState } from "react";
import Link from "next/link";
import { cn } from "@/lib/utils";
import { buttonVariants } from "../ui/button";

interface SideNavProps {
  items: NavItem[];
  className?: string;
  setOpen?: (open: boolean) => void;
}

export default function SideNav({ items, className, setOpen }: SideNavProps) {
  const path = usePathname();
  const { isOpen } = useSidebarStore();
  const [openItem, setOpenItem] = useState("");
  const [lastOpenItem, setLastOpenItem] = useState("");

  return (
    <nav className="space-y-2">
      {items.map((item) => (
        <Link
          key={item.title}
          href={item.href}
          onClick={() => {
            if (setOpen) setOpen(false);
          }}
          className={cn(
            buttonVariants({ variant: "ghost" }),
            "group relative flex h-12 justify-start",
            path === item.href && "bg-muted font-bold hover:bg-muted"
          )}
        >
          <item.icon className={cn("h-5 w-5", item.color)} />
          <span
            className={cn(
              "absolute left-12 text-base duration-200",
              !isOpen && className
            )}
          >
            {item.title}
          </span>
        </Link>
      ))}
    </nav>
  );
}

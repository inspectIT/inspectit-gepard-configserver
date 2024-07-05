import { useEffect, useState } from "react";
import { Sheet, SheetContent, SheetTrigger } from "../shadcn/sheet";
import { MenuIcon } from "lucide-react";
import SideNav from "./Sidenav";
import { NAV_ITEMS } from "./NavItems";

export default function MobileSidebar() {
  const [open, setOpen] = useState(false);

  return (
    <>
      <Sheet open={open} onOpenChange={setOpen}>
        <SheetTrigger asChild>
          <div className="flex items-center justify-center gap-2">
            <MenuIcon />
            <h1 className="text-lg font-semibold">Gepard Config Server</h1>
          </div>
        </SheetTrigger>
        <SheetContent side="left" className="w-72">
          <div className="px-1 py-6 pt-16">
            <SideNav items={NAV_ITEMS} setOpen={setOpen} />
          </div>
        </SheetContent>
      </Sheet>
    </>
  );
}

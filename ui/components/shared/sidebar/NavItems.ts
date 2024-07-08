import { title } from "process";
import { MdOutlineSupportAgent } from "react-icons/md";

/*
Here are all the navigation items that will be displayed in the sidebar.
Adding new pages in the router might require adding a new item here.
*/
export const NAV_ITEMS = [
  {
    title: "Connections",
    href:
      process.env.NODE_ENV == "production"
        ? "/ui/connections/"
        : "/connections/",
    icon: MdOutlineSupportAgent,
    color: "text-sky-500",
  },
];

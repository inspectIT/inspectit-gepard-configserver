import { MdOutlineSupportAgent } from "react-icons/md";

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

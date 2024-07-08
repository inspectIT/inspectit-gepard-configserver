import { IconType } from "react-icons";

/*
NavItem is an interface that defines the structure of the sidebar navigation items.
The Icon is provided as a react-icon component, and the color is optional.
*/
export interface NavItem {
  title: string;
  href: string;
  icon: IconType;
  color?: string;
  isChidren?: boolean;
  children?: NavItem[];
}

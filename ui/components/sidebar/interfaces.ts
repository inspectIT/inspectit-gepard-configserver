import { IconType } from "react-icons";

export interface NavItem {
  title: string;
  href: string;
  icon: IconType;
  color?: string;
  isChidren?: boolean;
  children?: NavItem[];
}

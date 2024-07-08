import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";

// This function is used to merge Tailwind classes with other classes, which are provided e.g. via props
export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

import { cleanup, fireEvent, render, screen } from "@testing-library/react";

import { beforeEach, describe, expect, test, vi } from "vitest";
import SideNav from "../Sidenav";
import { usePathname } from "next/navigation";
import { MdOutlineSupportAgent } from "react-icons/md";

// Mock multiple NavItems
const NAV_ITEMS = [
  {
    title: "Connections",
    href:
      process.env.NODE_ENV == "production"
        ? "/ui/connections/"
        : "/connections/",
    icon: MdOutlineSupportAgent,
    color: "text-sky-500",
  },
  {
    title: "Mappings",
    href: process.env.NODE_ENV == "production" ? "/ui/mappings/" : "/mappings/",
    icon: MdOutlineSupportAgent,
    color: "text-sky-500",
  },
];

// Mock usePathname
vi.mock("next/navigation", () => ({
  usePathname() {
    return "/connections/";
  },
}));

describe("SideNav Component Open", () => {
  // Mock useSidebarStore custom hook
  vi.mock("../useSidebarStore", () => ({
    useSidebarStore: vi.fn().mockReturnValue({ isOpen: true }),
  }));

  const mockSetOpen = vi.fn();

  let anchorElements: HTMLAnchorElement[];
  beforeEach(() => {
    cleanup();
    // Arrange
    render(<SideNav items={NAV_ITEMS} setOpen={mockSetOpen} />);
    anchorElements = screen.getAllByRole("link");
  });

  test("renders all NavItems with text", () => {
    expect(anchorElements.length).toBe(2);
    NAV_ITEMS.forEach((item) => {
      screen.getByText(item.title);
    });
  });

  test("renders inactive NavItem without bg-muted", () => {
    const classListArray: string[] = Array.from(
      anchorElements.at(0)!.classList
    );
    expect(classListArray).toContain("bg-muted");
  });

  test("renders active NavItem with bg-muted", () => {
    const classListArray: string[] = Array.from(
      anchorElements.at(1)!.classList
    );
    expect(classListArray).not.toContain("bg-muted");
  });

  test("calles setOpen, when link is clicked", () => {
    anchorElements.forEach((anchor) => {
      fireEvent.click(anchor);
    });
    expect(mockSetOpen).toHaveBeenCalledTimes(anchorElements.length);
  });
});

describe("SideNav Component Closed", () => {
  // Mock useSidebarStore custom hook
  vi.mock("../useSidebarStore", () => ({
    useSidebarStore: vi.fn().mockReturnValue({ isOpen: false }),
  }));

  let anchorElements: HTMLAnchorElement[];
  beforeEach(() => {
    cleanup();
    // Arrange
    render(<SideNav items={NAV_ITEMS} className="TEST-CLASS" />);
    anchorElements = screen.getAllByRole("link");
  });

  test("renders inactive NavItem with additional classes (e.g. to hide the text)", () => {
    const navItemTitleSpan: HTMLSpanElement = screen.getByText("Connections");
    expect(navItemTitleSpan.classList).toContain("TEST-CLASS");
  });
});

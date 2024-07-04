import { describe, expect, test, vi } from "vitest";
import Sidebar from "../Sidebar";
import { fireEvent, render, screen } from "@testing-library/react";

describe("Sidebar Component", () => {
  test("width of sidebar changes on toggle click", () => {
    render(<Sidebar />);

    const sidebar: HTMLElement = screen.getByRole("sidebar");
    expect(sidebar).toBeDefined();
    expect(Array.from(sidebar.classList)).toContain("w-52");

    const sidebarToggle: HTMLElement = screen.getByRole("sidebar-toggle");
    expect(sidebarToggle).toBeDefined();

    // We´re actually testing the useSidebarHook here too...
    fireEvent.click(sidebarToggle);

    expect(Array.from(sidebar.classList)).toContain("w-[78px]");
  });
});

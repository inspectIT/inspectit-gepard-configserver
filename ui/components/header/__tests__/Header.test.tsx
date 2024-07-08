import {
    render,
    screen,
    cleanup
  } from "@testing-library/react";
import { beforeEach, describe, expect, test } from "vitest";
import Header from "../Header";

describe("Header component", () => {
  beforeEach(() => {
    cleanup();
  });

  test("renders with correct link", () => {
    // Arrange
    render(<Header />);
    const homeLink = screen.getByRole("link");

    // Assert
    expect(homeLink).toBeDefined();
    expect(homeLink.getAttribute("href")).toBe("/");
  });
});
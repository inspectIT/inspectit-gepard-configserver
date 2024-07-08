import { cleanup, render } from "@testing-library/react";

import { beforeEach, describe, expect, test, vi } from "vitest";
import MobileSidebar from "../MobileSidebar";

vi.mock("", () => {});

describe("MobileSidebar Component", () => {
  beforeEach(() => {
    cleanup();
  });

  test("Sidebar mounted", () => {
    const rerender = render(<MobileSidebar/>);

    expect(rerender).toMatchSnapshot();
  });
});

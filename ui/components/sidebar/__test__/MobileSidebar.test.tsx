import { render } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import MobileSidebar from "../MobileSidebar";

describe("MobileSidebar Component", () => {
  test("Sidebar mounted", () => {
    const rerender = render(<MobileSidebar/>);

    expect(rerender).toMatchSnapshot();
  });
});

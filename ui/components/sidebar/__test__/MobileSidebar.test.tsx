import { render } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import MobileSidebar from "../MobileSidebar";

describe("MobileSidebar Component", () => {
  test("matches snapshot", () => {
    const rerender = render(<MobileSidebar/>);

    expect(rerender).toMatchSnapshot();
  });
});

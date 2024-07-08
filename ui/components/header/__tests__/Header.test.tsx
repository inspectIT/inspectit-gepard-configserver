import { render } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import Header from "../Header";

describe("Header component", () => {
  test("matches snapshot", () => {
    const rerender = render(<Header />);

    expect(rerender).toMatchSnapshot();
  });
});

import { describe, expect, test } from "vitest";
import { render } from "@testing-library/react";
import AgentScopeForm from "../AgentScopeForm";

describe("AgentScopeForm Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentScopeForm connectionId={"00000000-0000-0000-0000-000000000000"} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

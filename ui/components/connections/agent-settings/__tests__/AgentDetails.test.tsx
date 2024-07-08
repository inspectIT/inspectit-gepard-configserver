import { describe, expect, test } from "vitest";
import AgentDetails from "../AgentDetails";
import { TestConnectionsSmall } from "../../__tests__/TestConnections";
import { render } from "@testing-library/react";

describe("AgentDetails Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentDetails connection={TestConnectionsSmall.at(0)!} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

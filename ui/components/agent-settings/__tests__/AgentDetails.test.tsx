import { describe, expect, test } from "vitest";
import AgentDetails from "../components/AgentDetails";
import { render } from "@testing-library/react";
import { TestConnectionsSmall } from "@/components/connections/__tests__/TestConnections";

describe("AgentDetails Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentDetails connection={TestConnectionsSmall.at(0)!} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

import { describe, expect, test } from "vitest";

import { TestConnectionsSmall } from "../../__tests__/TestConnections";
import { render } from "@testing-library/react";
import AgentDetails from "@/components/agent-settings/components/AgentDetails";

describe("AgentDetails Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentDetails connection={TestConnectionsSmall.at(0)!} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

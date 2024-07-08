import { describe, expect, test } from "vitest";
import { TestConnectionsSmall } from "../../__tests__/TestConnections";
import { render } from "@testing-library/react";
import AgentSettingsView from "@/components/agent-settings/components/AgentSettingsView";


describe("AgentSettings Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentSettingsView connection={TestConnectionsSmall.at(0)!} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

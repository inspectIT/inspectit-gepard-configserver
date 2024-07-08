import { describe, expect, test } from "vitest";
import { render } from "@testing-library/react";
import AgentSettingsView from "../components/AgentSettingsView";
import { TestConnectionsSmall } from "@/components/connections/__tests__/TestConnections";

describe("AgentSettings Page", () => {
  test("matches snapshot", () => {
    const rerender = render(
      <AgentSettingsView connection={TestConnectionsSmall.at(0)!} />
    );
    expect(rerender).toMatchSnapshot();
  });
});

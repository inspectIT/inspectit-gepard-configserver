import { expect, test, describe, vi, beforeEach } from "vitest";
import {
  cleanup,
  fireEvent,
  render,
  renderHook,
  screen,
  waitFor,
} from "@testing-library/react";
import { TestConnectionsBig, TestConnectionsSmall } from "./TestConnections";
import { useConnections } from "../hooks/useConnections";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Connection } from "../interfaces/Connection";
import ConnectionsView from "../components/ConnectionsView";

const nock = require("nock");

describe("Connection Table", () => {
  beforeEach(() => {
    cleanup();
  });

  const createWrapper = () => {
    // creates a new QueryClient for each test
    const queryClient = new QueryClient({
      defaultOptions: {
        queries: {
          retry: false,
        },
      },
    });

    // eslint-disable-next-line
    return ({ children }) => (
      <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
    );
  };

  const mockApiCallResponse = (connectionsToReturn: Connection[]) => {
    return (
      nock("http://localhost:3000")
        .get("/connections")
        // 9 Elements here
        .reply(200, connectionsToReturn)
    );
  };

  // Testing the useConnections-Hook in Isolation
  test("useConnections return data", async () => {
    mockApiCallResponse(TestConnectionsSmall);
    const { result } = renderHook(() => useConnections(), {
      wrapper: createWrapper(),
    });

    await waitFor(() => expect(result.current.isSuccess).toBe(true));
    await waitFor(() => expect(result.current.data).toBeDefined());
  });

  test("ConnectionsTable renders all connections", async () => {
    mockApiCallResponse(TestConnectionsSmall);
    const Wrapper = createWrapper();

    render(
      <Wrapper>
        <ConnectionsView />
      </Wrapper>
    );

    const TableRows = await waitFor(() => screen.getAllByRole("row"));
    TableRows.forEach((row) => {
      console.log(row.textContent);
    });
    // All Elements + Header
    expect(TableRows.length).toBe(TestConnectionsSmall.length + 1);
  });

  test("ConnectionsTable renders not more than 10 Elements", async () => {
    const Wrapper = createWrapper();
    mockApiCallResponse(TestConnectionsBig);
    render(
      <Wrapper>
        <ConnectionsView />
      </Wrapper>
    );

    const TableRows = await waitFor(() => screen.getAllByRole("row"));
    TableRows.forEach((row) => {
      console.log(row.textContent);
    });
    // 10 Elements + Header
    expect(TableRows.length).toBe(11);
  });

  test('ConnectionsTable renders other elements at click on "next" and "previous".', async () => {
    const Wrapper = createWrapper();
    mockApiCallResponse(TestConnectionsBig);
    render(
      <Wrapper>
        <ConnectionsView />
      </Wrapper>
    );
    const Table = await waitFor(() => screen.getByRole("table"));
    const nextButton = await waitFor(() => screen.getByText("Next"));
    const previousButton = await waitFor(() => screen.getByText("Previous"));

    // Check that the initial elements (with pid 11-20) are rendered in the table
    const TableRows = await waitFor(() => screen.getAllByRole("row"));

    // 10 Elements + Header
    await waitFor(() => {
      // Element with service name agent-first-page should be defined
      expect(screen.getByText("agent-first-page")).toBeDefined();
      expect(screen.queryByText("agent-second-page")).toBeNull();
    });

    fireEvent.click(nextButton);
    // 10 Elements + Header
    await waitFor(() => {
      // Element with service name agent-second-page should be defined
      expect(screen.queryByText("agent-first-page")).toBeNull();
      expect(screen.getByText("agent-second-page")).toBeDefined();
    });

    fireEvent.click(previousButton);

    await waitFor(() => {
      // Element with service name agent-first-page should be defined
      expect(screen.getByText("agent-first-page")).toBeDefined();
      expect(screen.queryByText("agent-second-page")).toBeNull();
    });
  });
});

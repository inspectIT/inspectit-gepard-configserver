import { beforeEach, describe, expect, it, test, vi } from "vitest";
import { cleanup, render, screen } from "@testing-library/react";
import Page from "@/app/page";

import Providers from "@/app/providers";
import ConnectionTable from "../ConnectionTable";
import DataTable from "@/components/shadcn/DataTable";
import { SetStateAction } from "react";
import { afterEach } from "node:test";
import { randomUUID } from "node:crypto";
import { connectionColumns } from "../ConnectionColumns";
import { NIL as NIL_UUID } from "uuid";
import { Connection } from "../Connection";
import { TestConnections } from "./TestConnections";

const setup = (data: Connection[]) => {
  return render(
    <Providers>
      <DataTable
        columns={[]}
        data={[]}
        globalFilter={""}
        setGlobalFilter={function (value: SetStateAction<string>): void {
          throw new Error("Function not implemented.");
        }}
      />
    </Providers>
  );
};

describe("ConnectionsPage No Data", () => {
  let containerWithoutData: HTMLElement;

  // Arrange
  beforeEach(() => {
    cleanup();
    const renderedDom = setup([]);
    containerWithoutData = renderedDom.container;
  });

  afterEach(() => {
    cleanup();
  });

  it("renders successfully", async () => {
    expect(containerWithoutData).toMatchSnapshot();
    cleanup();
  });

  it('renders "no results" without data', async () => {
    const text = await screen.findByText("No results.");
    cleanup();
  });
});

describe("ConnectionsPage with Data", () => {
  let containerWithData: HTMLElement;
  beforeEach(() => {
    cleanup();
    const renderedDom = setup(TestConnections);
    containerWithData = renderedDom.container;
  });

  it("renders result with connections data", async () => {
    expect(containerWithData).toMatchSnapshot();
  });
});

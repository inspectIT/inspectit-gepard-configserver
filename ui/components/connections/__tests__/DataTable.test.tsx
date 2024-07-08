import { beforeEach, describe, expect, it, test, vi } from "vitest";
import { cleanup, render, screen } from "@testing-library/react";
import Page from "@/app/page";

import Providers from "@/app/providers";

import { SetStateAction } from "react";
import { Connection } from "../interfaces/Connection";
import DataTable from "@/components/shared/shadcn/DataTable";
import { TestConnectionsBig } from "./TestConnections";

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

describe("Data Table No Data", () => {
  let containerWithoutData: HTMLElement;

  // Arrange
  beforeEach(() => {
    cleanup();
    const renderedDom = setup([]);
    containerWithoutData = renderedDom.container;
  });

  it("renders successfully", async () => {
    expect(containerWithoutData).toMatchSnapshot();
  });

  it('renders "no results" without data', async () => {
    const text = await screen.findByText("No results.");
  });
});

describe("Data Table with Data", () => {
  let containerWithData: HTMLElement;
  beforeEach(() => {
    const renderedDom = setup(TestConnectionsBig);
    containerWithData = renderedDom.container;
  });

  it("renders result with connections data", async () => {
    expect(containerWithData).toMatchSnapshot();
  });
});
